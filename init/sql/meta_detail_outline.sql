create or replace view `bigdata-backstage`.view_dim_gfdn_meta_detail_outline
as
select `tbl_level`       AS `tbl_level`,
       `tbl_name`        AS `tbl_name`,
       `tbl_comment`     AS `tbl_comment`,
       `col_name`        AS `col_name`,
       `col_type`        AS `col_type`,
       `col_comment`     AS `col_comment`,
       `tbl_col_num`     AS `tbl_col_num`,
       `all_row_count`   AS `all_row_count`,
       `row_count`       AS `row_count`,
       `index1_name`     AS `index1_name`,
       `index2_name`     AS `index2_name`,
       `index3_name`     AS `index3_name`,
       `is_pt`           AS `is_pt`,
       `pt_last`         AS `pt_last`,
       `tbl_create_time` AS `tbl_create_time`,
       `tbl_update_time` AS `tbl_update_time`,
       `tbl_lifecycle`   AS `tbl_lifecycle`,
       `tbl_type`        AS `tbl_type`,
       `tbl_size`        AS `tbl_size`,
       `etl_tm`          AS `etl_tm`,
       `dt`              AS `dt`
from `gfdn_odps_export`.`dim_gfdn_meta_detail_outline`;

create or replace view `bigdata-backstage`.view_dim_qygc_meta_detail_outline
as
select `tbl_level`       AS `tbl_level`,
       `tbl_name`        AS `tbl_name`,
       `tbl_comment`     AS `tbl_comment`,
       `col_name`        AS `col_name`,
       `col_type`        AS `col_type`,
       `col_comment`     AS `col_comment`,
       `tbl_col_num`     AS `tbl_col_num`,
       `all_row_count`   AS `all_row_count`,
       `row_count`       AS `row_count`,
       `index1_name`     AS `index1_name`,
       `index2_name`     AS `index2_name`,
       `index3_name`     AS `index3_name`,
       `is_pt`           AS `is_pt`,
       `pt_last`         AS `pt_last`,
       `tbl_create_time` AS `tbl_create_time`,
       `tbl_update_time` AS `tbl_update_time`,
       `tbl_lifecycle`   AS `tbl_lifecycle`,
       `tbl_type`        AS `tbl_type`,
       `tbl_size`        AS `tbl_size`,
       `etl_tm`          AS `etl_tm`,
       `dt`              AS `dt`
from `gfdn_odps_export`.`dim_qygc_meta_detail_outline`;

create or replace view `bigdata-backstage`.view_dim_e_meta_detail_outline
as
select `tbl_level`       AS `tbl_level`,
       `tbl_name`        AS `tbl_name`,
       `tbl_comment`     AS `tbl_comment`,
       `col_name`        AS `col_name`,
       `col_type`        AS `col_type`,
       `col_comment`     AS `col_comment`,
       `tbl_col_num`     AS `tbl_col_num`,
       `all_row_count`   AS `all_row_count`,
       `row_count`       AS `row_count`,
       `index1_name`     AS `index1_name`,
       `index2_name`     AS `index2_name`,
       `index3_name`     AS `index3_name`,
       `is_pt`           AS `is_pt`,
       `pt_last`         AS `pt_last`,
       `tbl_create_time` AS `tbl_create_time`,
       `tbl_update_time` AS `tbl_update_time`,
       `tbl_lifecycle`   AS `tbl_lifecycle`,
       `tbl_type`        AS `tbl_type`,
       `tbl_size`        AS `tbl_size`,
       `etl_tm`          AS `etl_tm`,
       `dt`              AS `dt`
from `gfdn_odps_export`.`dim_e_meta_detail_outline`;