-- 数据资产-ODPS表
drop table if exists `met_data_table`;
CREATE TABLE if not exists `met_data_table`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`           int(10)             NOT NULL COMMENT '数仓ID',
    `tbl_name`        varchar(255)        NOT NULL COMMENT '表名',
    `tbl_comment`     varchar(255)        NOT NULL COMMENT '表备注',
    `tbl_create_time` datetime            NOT NULL COMMENT '表创建日期',
    `tbl_update_time` datetime            NOT NULL COMMENT '表更新日期',
    `is_pt`           tinyint(1)     DEFAULT NULL COMMENT '是否是分区表 1-是 0-否',
    `pt_last`         varchar(50)    DEFAULT NULL COMMENT '最新分区',
    `tbl_lifecycle`   int(10)        DEFAULT NULL COMMENT '表生命周期',
    `tbl_type`        varchar(50)    DEFAULT NULL COMMENT '表类型 内部表/外部表',
    `tbl_size`        decimal(16, 2) DEFAULT NULL COMMENT '表大小 单位:MB',
    `tbl_col_num`     int(10)        DEFAULT NULL COMMENT '字段数',
    `all_row_count`   int(10)        DEFAULT NULL COMMENT '总行数',
    `row_count`       int(10)        DEFAULT NULL COMMENT '最新分区行数',
    `create_time`     datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      tinyint(1)     DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据资产-ODPS表';

-- 数据资产-ODPS表字段
drop table if exists `met_data_column`;
CREATE TABLE if not exists `met_data_column`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`       int(10)             NOT NULL COMMENT '数仓ID',
    `tbl_name`    varchar(255)        NOT NULL COMMENT '表名',
    `col_name`    varchar(50)  DEFAULT NULL COMMENT '字段名',
    `col_type`    varchar(50)  DEFAULT NULL COMMENT '字段类型',
    `col_comment` varchar(255) DEFAULT NULL COMMENT '字段备注',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)   DEFAULT 0 COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE,
    index `idx_tbl_name` (`tbl_name`) USING BTREE,
    index `idx_col_name` (`col_name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据资产-ODPS表字段';


-- 数据资产-数据同步日志表
drop table if exists `met_data_sync_log`;
CREATE TABLE if not exists `met_data_sync_log`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`        varchar(50)         NOT NULL COMMENT '同步名称',
    `spend_times` int(5)              NOT NULL COMMENT '花费时间单位 : 秒',
    `start_time`  datetime COMMENT '开始时间',
    `end_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='数据资产-数据同步日志表';