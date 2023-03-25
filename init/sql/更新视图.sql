create or replace view `view_max_dt` as
select max(dt) as dt
from (select dt, count(*)
      from (select dt, dw_id
            from view_met_detail_outline
            group by dt, dw_id) t1
      group by dt
      having count(*) = 2) t2;

-- 元数据宽表
create or replace view `view_met_detail_outline`
as
select 1 as dw_id
     , tbl_level
     , tbl_name
     , tbl_comment
     , col_name
     , col_type
     , col_comment
     , tbl_col_num
     , all_row_count
     , row_count
     , index1_name
     , index2_name
     , index3_name
     , is_pt
     , pt_last
     , tbl_create_time
     , tbl_update_time
     , tbl_lifecycle
     , tbl_type
     , tbl_size
     , etl_tm
     , dt
from gfdn_odps_export.dim_gfdn_meta_detail_outline
union all
select 2 as dw_id
     , tbl_level
     , tbl_name
     , tbl_comment
     , col_name
     , col_type
     , col_comment
     , tbl_col_num
     , all_row_count
     , row_count
     , index1_name
     , index2_name
     , index3_name
     , is_pt
     , pt_last
     , tbl_create_time
     , tbl_update_time
     , tbl_lifecycle
     , tbl_type
     , tbl_size
     , etl_tm
     , dt
from gfdn_odps_export.dim_qygc_meta_detail_outline;


-- 数据血缘
create or replace view `view_met_blood_relation_detail`
as
select 1 as dw_id
     , ins_name
     , tbl_out_en
     , tbl_out_zh
     , tbl_in_en
     , tbl_in_zh
     , etl_tm
     , dt
from gfdn_odps_export.dim_gfdn_meta_blood_relation_detail
union all
select 2 as dw_id
     , ins_name
     , tbl_out_en
     , tbl_out_zh
     , tbl_in_en
     , tbl_in_zh
     , etl_tm
     , dt
from gfdn_odps_export.dim_qygc_meta_blood_relation_detail;



-- 数据质量
create or replace view `view_met_quality`
as
select 1                                                  as dw_id
     , table_name
     , table_comment
     , col_name
     , col_comment
     , distinct_count
     , total_count
     , max_len
     , min_len
     , max_value
     , min_value
     , null_count
     , null_rate
     , spend_time
     , etl_tm
     , date_format(str_to_date(dt, '%Y%m%d'), '%Y-%m-%d') as dt
from gfdn_odps_export.dim_gfdn_meta_quality
union all
select 2                                                  as dw_id
     , table_name
     , table_comment
     , col_name
     , col_comment
     , distinct_count
     , total_count
     , max_len
     , min_len
     , max_value
     , min_value
     , null_count
     , null_rate
     , spend_time
     , etl_tm
     , date_format(str_to_date(dt, '%Y%m%d'), '%Y-%m-%d') as dt
from gfdn_odps_export.dim_qygc_meta_quality;



-- 视图原子性表级别
create or replace view `view_met_data_table`
as
select dw_id,
       tbl_name,
       max(tbl_level)       as tbl_level,
       max(index1_name)     as index1_name,
       max(index2_name)     as index2_name,
       max(index3_name)     as index3_name,
       max(tbl_comment)     as tbl_comment,
       max(tbl_create_time) as tbl_create_time,
       max(tbl_update_time) as tbl_update_time,
       max(is_pt)           as is_pt,
       max(pt_last)         as pt_last,
       max(tbl_lifecycle)   as tbl_lifecycle,
       max(tbl_type)        as tbl_type,
       max(tbl_size)        as tbl_size,
       max(tbl_col_num)     as tbl_col_num,
       max(all_row_count)   as all_row_count,
       max(row_count)       as row_count
from (select dw_id,
             tbl_name,
             tbl_level,
             index1_name,
             index2_name,
             index3_name,
             tbl_comment,
             tbl_create_time,
             tbl_update_time,
             tbl_size,
             is_pt,
             tbl_type,
             tbl_lifecycle,
             pt_last,
             tbl_col_num,
             all_row_count,
             row_count
      from view_met_detail_outline
      where dt = (select dt
                  from view_max_dt)) t
group by dw_id, tbl_name;


-- 视图原子性字段级别
create or replace view `view_met_data_column`
as
select dw_id, tbl_name, col_name, col_type, col_comment
from (select dw_id, tbl_name, col_name, col_type, col_comment
      from view_met_detail_outline
      where dt = (select dt
                  from view_max_dt)) t
group by dw_id, tbl_name, col_name, col_type, col_comment;


-- 视图原子性表级别(勘探)
create or replace view `view_explore_data_table`
as
select dw_id,
       table_name,
       table_comment,
       sum(distinct_count) as distinct_count,
       sum(total_count)    as total_count,
       max(spend_time)     as spend_time,
       min(etl_tm)         as etl_tm
from met_explore_report
group by dw_id, table_name, table_comment;


-- 视图原子性字段级别(勘探)
create or replace view `view_explore_data_column`
as
select dw_id,
       table_name,
       col_name,
       col_type,
       col_comment,
       max_len,
       min_len,
       max_value,
       min_value,
       null_count,
       null_rate,
       value_kind_json,
       is_only_value
from met_explore_report;



-- ------------------------------------------------------
create or replace view `view_dim_gfdn_meta_detail_outline`
as
select tbl_level
     , tbl_name
     , tbl_comment
     , col_name
     , col_type
     , col_comment
     , tbl_col_num
     , all_row_count
     , row_count
     , index1_name
     , index2_name
     , index3_name
     , is_pt
     , pt_last
     , tbl_create_time
     , tbl_update_time
     , tbl_lifecycle
     , tbl_type
     , tbl_size
     , etl_tm
     , dt
from gfdn_odps_export.dim_gfdn_meta_detail_outline;


create or replace view `view_dim_qygc_meta_detail_outline`
as
select tbl_level
     , tbl_name
     , tbl_comment
     , col_name
     , col_type
     , col_comment
     , tbl_col_num
     , all_row_count
     , row_count
     , index1_name
     , index2_name
     , index3_name
     , is_pt
     , pt_last
     , tbl_create_time
     , tbl_update_time
     , tbl_lifecycle
     , tbl_type
     , tbl_size
     , etl_tm
     , dt
from gfdn_odps_export.dim_qygc_meta_detail_outline;



