use backstage;

-- 元数据宽表
create or replace view `view_met_detail_outline`
as
select tbl_level
     , tbl_name
     , tbl_comment
     , col_name
     , col_type
     , col_comment
     , tbl_col_num
     , RAND() * 1000 as all_row_count
     , RAND() * 1000 as row_count
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
     , '20230206'    as dt
from gfdn_odps_export.dim_gfdn_meta_detail_outline;


-- 数据血缘
create or replace view `view_met_blood_relation_detail`
as
select ins_name
     , tbl_out_en
     , tbl_out_zh
     , tbl_in_en
     , tbl_in_zh
     , etl_tm
     , '20230206' as dt
from gfdn_odps_export.dim_gfdn_meta_blood_relation_detail;


-- 数据质量
create or replace view `view_met_quality`
as
select table_name
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
     , '20230206' as dt
from gfdn_odps_export.dim_gfdn_meta_quality;


-- #########################################################################
-- #########################################################################
-- #########################################################################
-- 数据标准-字根库
drop table if exists `met_norm_root`;
CREATE TABLE if not exists `met_norm_root`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name_zh`       varchar(50)         NOT NULL COMMENT '词根中文',
    `desc`          varchar(255) COMMENT '词根描述',
    `name_en`       varchar(255)        NOT NULL COMMENT '词根英文',
    `name_short_en` varchar(50)         NOT NULL COMMENT '词根英文简写',
    `create_user`   varchar(50)         NOT NULL COMMENT '创建人',
    `update_user`   varchar(50)         NOT NULL COMMENT '更新人',
    `create_time`   datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`    tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据标准-字根库';


-- 数据标准-字典库
drop table if exists `met_norm_dict`;
CREATE TABLE if not exists `met_norm_dict`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name_code`   varchar(50)         NOT NULL DEFAULT '' COMMENT '字典编码',
    `name`        varchar(50)         NOT NULL DEFAULT '' COMMENT '字典名称',
    `parent_id`   bigint(20)                   DEFAULT '0' COMMENT '上级字典名称id',
    `desc`        varchar(255) COMMENT '字典描述',
    `tree_path`   varchar(255)                 DEFAULT ',' COMMENT '树结构',
    `sort_value`  int(11)                      DEFAULT '1' COMMENT '排序',
    `create_user` varchar(50)         NOT NULL COMMENT '创建人',
    `update_user` varchar(50)         NOT NULL COMMENT '更新人',
    `status`      tinyint(1)                   DEFAULT '1' COMMENT '状态（1正常 0停用）',
    `create_time` datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)                   DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据标准-字典库';


-- 数据标准-任务节点
drop table if exists `met_norm_node`;
CREATE TABLE if not exists `met_norm_node`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`        varchar(50) COMMENT '节点类型/名称',
    `rule`        varchar(50) COMMENT '节点命名规范',
    `desc`        varchar(255) COMMENT '节点描述',
    `create_user` varchar(50)         NOT NULL COMMENT '创建人',
    `update_user` varchar(50)         NOT NULL COMMENT '更新人',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据标准-任务节点';

-- 数据标准-表命名规范
drop table if exists `met_norm_table`;
CREATE TABLE if not exists `met_norm_table`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `level`           varchar(50) COMMENT '小层层级名称',
    `rule`            varchar(50) COMMENT '命名标准版',
    `lifecycle`       varchar(50) COMMENT '生命周期',
    `desc`            varchar(255) COMMENT '描述',
    `example_name_zh` varchar(255) COMMENT '示例中文名称',
    `example_name_en` varchar(255) COMMENT '示例英文名称',
    `create_user`     varchar(50)         NOT NULL COMMENT '创建人',
    `update_user`     varchar(50)         NOT NULL COMMENT '更新人',
    `create_time`     datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据标准-表命名规范';


-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 数据勘探操作表
drop table if exists `met_explore_task`;
CREATE TABLE if not exists `met_explore_task`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `table`       varchar(50) COMMENT '勘探的表名',
    `create_user` varchar(50)         NOT NULL COMMENT '创建人',
    `create_date` date COMMENT '创建日期',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据勘探-数据勘探操作表';


-- 数据勘探报告表
drop table if exists `met_explore_report`;
CREATE TABLE if not exists `met_explore_report`
(
    `table_name`     varchar(255) COMMENT '表名',
    `table_comment`  varchar(255) COMMENT '表描述',
    `col_name`       varchar(255) COMMENT '字段名',
    `col_type`       varchar(255) COMMENT '字段类型',
    `col_comment`    varchar(255) COMMENT '字段描述',
    `distinct_count` varchar(255) COMMENT '去重记录数',
    `total_count`    varchar(255) COMMENT '总记录数(最近的分区)',
    `max_len`        varchar(255) COMMENT '最大长度',
    `min_len`        varchar(255) COMMENT '最小长度',
    `max_value`      varchar(255) COMMENT '最大值',
    `min_value`      varchar(255) COMMENT '最小值',
    `null_count`     varchar(255) COMMENT '空值数',
    `null_rate`      varchar(255) COMMENT '空值率',
    `value_quality`   varchar(255)   COMMENT '字段值质量,使用空值率判断:高(<30),中(30,80),低(>80)',
    `value_kind`   varchar(255)  COMMENT '种类(超过10个不显示),JSON格式化',
    `value_kind_json`  varchar(255)    COMMENT '值种类(所占百分比,超过15个不显示),JSON格式化',
    `value_status`   varchar(255)   COMMENT '值类型 空值类|唯一类|二分类|多分类|无穷类',
    `is_repeat`   varchar(255)  COMMENT '是否有重复值',
    `is_exist_null`    varchar(255)    COMMENT '是否存在null',
    `is_all_null`   varchar(255)    COMMENT '是否全为null',
    `is_only_value`  varchar(255)   COMMENT '是否全是相同的值',
    `is_diff_value`  varchar(255)    COMMENT '是否全是不同的值且没有空值',
    `spend_time`  varchar(255)    COMMENT '读取记录数花费的时间单位:秒',
    `etl_tm`   varchar(255)     COMMENT 'ETL时间',
    `dt`   varchar(255)    COMMENT '日期'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据勘探-数据勘探报告表';

-- -
