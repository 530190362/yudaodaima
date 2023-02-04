-- #######################################################################
--
--          政务网环境
--
-- #######################################################################


use backstage;

-- 表设计
-- 表前缀模块
# ums 权限模块
# met 数仓元数据模块

# -- 设置局部变量
# SET @dwID = 1;
# select @dwID;

-- (共富大脑仓)表的总数
create or replace view `view_gfdn_met_record_zip`
as
select '表总张数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1) t
union all
select '占磁盘大小' as name, sum(tbl_size) / 1024 as value
from (select max(tbl_size) as tbl_size
      from (select tbl_name, tbl_size
            from met_detail_outline
            where dw_id = 1) t1
      group by tbl_name) t2
union all
select '总字段个数' as name, sum(tbl_col_num) as value
from (select max(tbl_col_num) as tbl_col_num
      from (select tbl_name, tbl_col_num
            from met_detail_outline
            where dw_id = 1) t1
      group by tbl_name) t2
union all
select '总数据记录数据' as name, sum(row_num) as value
from (select max(row_num) as row_num
      from (select tbl_name, row_num
            from met_detail_outline
            where dw_id = 1) t1
      group by tbl_name) t2
union all
select 'ODS层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'ODS') t
union all
select 'DWD层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'DWD') t
union all
select 'DIM层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'DIM') t
union all
select 'DWS层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'DWS') t
union all
select 'DWT层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'DWT') t
union all
select 'DWM层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'DWM') t
union all
select 'ADS层表的总数' as name, count(*) as value
from (select distinct tbl_name
      from met_detail_outline
      where dw_id = 1
        and upper(tbl_level) = 'ADS') t;


-- 数仓主表
drop table if exists `met_dw_info`;
CREATE TABLE if not exists `met_dw_info`
(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_name_en`       varchar(50)  DEFAULT NULL COMMENT '数仓名称(英文)',
    `dw_name_zn`       varchar(50)  DEFAULT NULL COMMENT '数仓名称(中文)',
    `dw_desc`       varchar(255)  DEFAULT NULL COMMENT '数仓描述',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数仓主表';



-- ODPS元数据记录拉链表 使用 存储过程 + 定时器完成
drop table if exists `met_record_zip`;
CREATE TABLE if not exists `met_record_zip`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`       int(10)  DEFAULT NULL COMMENT '数仓id',

    `update_date` DATE     DEFAULT null COMMENT '更新日期',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='ODPS元数据记录拉链表';


-- 列转行
select
1 as dw_id,
SUM(IF(name ='表总张数',value,0)) AS '表总张数',
SUM(IF(name ='占磁盘大小',value,0)) AS '占磁盘大小',
SUM(IF(name ='总字段个数',value,0)) AS '总字段个数',
SUM(IF(name ='总数据记录数据',value,0)) AS '总数据记录数据',
SUM(IF(name ='ODS层表的总数',value,0)) AS 'ODS层表的总数',
SUM(IF(name ='DWD层表的总数',value,0)) AS 'DWD层表的总数',
SUM(IF(name ='DIM层表的总数',value,0)) AS 'DIM层表的总数',
SUM(IF(name ='DWS层表的总数',value,0)) AS 'DWS层表的总数',
SUM(IF(name ='DWT层表的总数',value,0)) AS 'DWT层表的总数',
SUM(IF(name ='DWM层表的总数',value,0)) AS 'DWM层表的总数',
SUM(IF(name ='ADS层表的总数',value,0)) AS 'ADS层表的总数',
current_date as update_date
from view_gfdn_met_record_zip;

