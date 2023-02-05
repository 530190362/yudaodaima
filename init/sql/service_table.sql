-- #######################################################################
--
--          政务网环境
--
-- #######################################################################


# set time_zone = '+08:00';
# -- 刷新 立刻生效
# flush privileges;


use backstage;

-- 表设计
-- 表前缀模块
# ums 权限模块
# met 数仓元数据模块

# -- 设置局部变量
# SET @dwID = 1;
# select @dwID;


-- 数仓主表
drop table if exists `met_dw_info`;
CREATE TABLE if not exists `met_dw_info`
(
    `id`          int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_name_en`  varchar(50)  DEFAULT NULL COMMENT '数仓名称(英文)',
    `dw_name_zn`  varchar(50)  DEFAULT NULL COMMENT '数仓名称(中文)',
    `dw_desc`     varchar(255) DEFAULT NULL COMMENT '数仓描述',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)   DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数仓主表';


-- ODPS元数据记录拉链表 使用 存储过程 + 定时器完成
drop table if exists `met_record_zip`;
CREATE TABLE if not exists `met_record_zip`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`           int(10)        DEFAULT NULL COMMENT '数仓id',
    `table_count`     int(10)        DEFAULT NULL COMMENT '表总张数',
    `disk_size`       decimal(16, 2) DEFAULT NULL COMMENT '数仓id',
    `col_count`       int(10)        DEFAULT NULL COMMENT '总字段个数',
    `row_count`       int(10)        DEFAULT NULL COMMENT '总数据记录数据',
    `ods_table_count` int(10)        DEFAULT NULL COMMENT 'ODS层表的总数',
    `dwd_table_count` int(10)        DEFAULT NULL COMMENT 'DWD层表的总数',
    `dim_table_count` int(10)        DEFAULT NULL COMMENT 'DIM层表的总数',
    `dws_table_count` int(10)        DEFAULT NULL COMMENT 'DWS层表的总数',
    `dwt_table_count` int(10)        DEFAULT NULL COMMENT 'DWT层表的总数',
    `dwm_table_count` int(10)        DEFAULT NULL COMMENT 'DWM层表的总数',
    `ads_table_count` int(10)        DEFAULT NULL COMMENT 'ADS层表的总数',
    `update_date`     DATE           DEFAULT null COMMENT '更新日期',
    `update_time`     DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='ODPS元数据记录拉链表';


-- UDF函数库
drop table if exists `met_udf_info`;
CREATE TABLE if not exists `met_udf_info`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',


    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',

    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='UDF函数库';


-- 标准库
drop table if exists `met_dw_standard`;
CREATE TABLE if not exists `met_dw_standard`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',


    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除 1-是 0-否',

    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='标准库';


-- 数据字典库
drop table if exists `met_data_dict`;
CREATE TABLE if not exists `met_data_dict`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`        varchar(50)         NOT NULL DEFAULT '' COMMENT '字典名称',
    `parent_id`   bigint(20)                   DEFAULT '0' COMMENT '上级字典名称id',
    `desc`        varchar(255)                 DEFAULT NULL COMMENT '字典描述',
    `tree_path`   varchar(255)                 DEFAULT ',' COMMENT '树结构',
    `sort_value`  int(11)                      DEFAULT '1' COMMENT '排序',
    `leader`      varchar(20)                  DEFAULT NULL COMMENT '负责人',
    `status`      tinyint(1)                   DEFAULT '1' COMMENT '状态（1正常 0停用）',
    `create_time` datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)                   DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据字典库';





