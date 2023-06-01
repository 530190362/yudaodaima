create or replace view `bigdata-backstage`.view_dim_gfdn_met_blood_relation_detail
as
select
    `ins_name`    ,
    `tbl_out_en`  ,
    `tbl_out_zh`  ,
    `tbl_in_en`   ,
    `tbl_in_zh`   ,
    `etl_tm`
from `gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`;

create or replace view `bigdata-backstage`.view_dim_qygc_met_blood_relation_detail
as
select
    `ins_name`    ,
    `tbl_out_en`  ,
    `tbl_out_zh`  ,
    `tbl_in_en`   ,
    `tbl_in_zh`   ,
    `etl_tm`
from `gfdn_odps_export`.`dim_qygc_meta_blood_relation_detail`;

create or replace view `bigdata-backstage`.view_dim_e_met_blood_relation_detail
as
select
    `ins_name`    ,
    `tbl_out_en`  ,
    `tbl_out_zh`  ,
    `tbl_in_en`   ,
    `tbl_in_zh`   ,
    `etl_tm`
from `gfdn_odps_export`.`dim_e_meta_blood_relation_detail`;