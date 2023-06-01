create or replace view `bigdata-backstage`.view_dim_gfdn_meta_quality
as
select
    `table_name`     ,
    `table_comment`  ,
    `col_name`       ,
    `col_comment`    ,
    `distinct_count` ,
    `total_count`    ,
    `max_len`        ,
    `min_len`        ,
    `max_value`      ,
    `min_value`      ,
    `null_count`     ,
    `null_rate`      ,
    `spend_time`     ,
    `etl_tm`         ,
    `dt`
from `gfdn_odps_export`.`dim_gfdn_meta_quality`;

create or replace view `bigdata-backstage`.view_dim_qygc_meta_quality
as
select
    `table_name`     ,
    `table_comment`  ,
    `col_name`       ,
    `col_comment`    ,
    `distinct_count` ,
    `total_count`    ,
    `max_len`        ,
    `min_len`        ,
    `max_value`      ,
    `min_value`      ,
    `null_count`     ,
    `null_rate`      ,
    `spend_time`     ,
    `etl_tm`         ,
    `dt`
from `gfdn_odps_export`.`dim_qygc_meta_quality`;

create or replace view `bigdata-backstage`.view_dim_e_meta_quality
as
select
    `table_name`     ,
    `table_comment`  ,
    `col_name`       ,
    `col_comment`    ,
    `distinct_count` ,
    `total_count`    ,
    `max_len`        ,
    `min_len`        ,
    `max_value`      ,
    `min_value`      ,
    `null_count`     ,
    `null_rate`      ,
    `spend_time`     ,
    `etl_tm`         ,
    `dt`
from `gfdn_odps_export`.`dim_e_meta_quality`;