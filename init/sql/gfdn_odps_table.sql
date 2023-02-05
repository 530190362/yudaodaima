-- #######################################################################
--
--          政务网环境
--
-- #######################################################################


use backstage;

-- 元数据宽表
drop table if exists `met_detail_outline`;
CREATE TABLE if not exists `met_detail_outline`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`           int(10)        DEFAULT NULL COMMENT '数仓id',
    `tbl_level`       varchar(50)    DEFAULT NULL COMMENT '表层级',
    `tbl_name`        varchar(255)   DEFAULT NULL COMMENT '表英文名',
    `tbl_comment`     varchar(255)   DEFAULT NULL COMMENT '表中文名',
    `col_name`        varchar(50)    DEFAULT NULL COMMENT '字段名',
    `col_type`        varchar(50)    DEFAULT NULL COMMENT '字段类型',
    `col_comment`     varchar(255)   DEFAULT NULL COMMENT '字段备注',
    `tbl_col_num`     int(10)        DEFAULT NULL COMMENT '字段数',
    `row_num`         int(10)        DEFAULT NULL COMMENT '行数',
    `index1_name`     varchar(50)    DEFAULT NULL COMMENT '表一级明细',
    `index2_name`     varchar(50)    DEFAULT NULL COMMENT '表二级明细',
    `index3_name`     varchar(50)    DEFAULT NULL COMMENT '表三级明细',
    `is_pt`           tinyint(1)     DEFAULT NULL COMMENT '是否是分区表 1-是 0-否',
    `pt_last`         varchar(50)    DEFAULT NULL COMMENT '最新分区',
    `tbl_create_time` datetime       DEFAULT NULL COMMENT '表创建时间',
    `tbl_update_time` datetime       DEFAULT NULL COMMENT '表更新时间',
    `tbl_lifecycle`   int(10)        DEFAULT NULL COMMENT '表生命周期',
    `tbl_type`        varchar(50)    DEFAULT NULL COMMENT '表类型 内部表/外部表',
    `tbl_size`        decimal(16, 2) DEFAULT NULL COMMENT '表大小 单位:MB',
    `etl_tm`          varchar(50)    DEFAULT NULL COMMENT 'ETL时间',
    `dt`              varchar(50)    DEFAULT NULL COMMENT '分区',
    `create_time`     datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='ODPS元数据宽表';

-- 数据血缘表
drop table if exists `met_blood_relation_detail`;
CREATE TABLE if not exists `met_blood_relation_detail`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`       int(10)      DEFAULT NULL COMMENT '数仓id',
    `ins_name`    varchar(50)  DEFAULT NULL COMMENT '调度节点名称',
    `tbl_out_en`  varchar(255) DEFAULT NULL COMMENT '输出表英文名',
    `tbl_out_zh`  varchar(255) DEFAULT NULL COMMENT '输出表中文名',
    `tbl_in_en`   varchar(255) DEFAULT NULL COMMENT '输入表英文名',
    `tbl_in_zh`   varchar(255) DEFAULT NULL COMMENT '输入表中文名',
    `etl_tm`      varchar(50)  DEFAULT NULL COMMENT 'ETL时间',
    `dt`          varchar(50)  DEFAULT NULL COMMENT '分区',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='odps数据血缘明细表';

-- 数据质量表
drop table if exists `met_quality`;
CREATE TABLE if not exists `met_quality`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`          int(10)        DEFAULT NULL COMMENT '数仓id',
    `table_name`     varchar(255)   DEFAULT NULL COMMENT '表名',
    `table_comment`  varchar(255)   DEFAULT NULL COMMENT '表备注',
    `col_name`       varchar(50)    DEFAULT NULL COMMENT '字段名',
    `col_comment`    varchar(255)   DEFAULT NULL COMMENT '字段备注',
    `distinct_count` int(10)        DEFAULT NULL COMMENT '去重记录数',
    `total_count`    int(10)        DEFAULT NULL COMMENT '总记录数(最近的分区)',
    `max_len`        varchar(50)    DEFAULT NULL COMMENT '最大长度',
    `min_len`        varchar(50)    DEFAULT NULL COMMENT '最小长度',
    `max_value`      varchar(255)   DEFAULT NULL COMMENT '最大值',
    `min_value`      varchar(255)   DEFAULT NULL COMMENT '最小值',
    `null_count`     decimal(16, 2) DEFAULT NULL COMMENT '空值数',
    `null_rate`      decimal(16, 2) DEFAULT NULL COMMENT '空值率',
    `spend_time`     int(10)        DEFAULT NULL COMMENT '读取记录数花费的时间单位:秒',
    `etl_tm`         varchar(255)   DEFAULT NULL COMMENT 'ETL时间',
    `dt`             varchar(255)   DEFAULT NULL COMMENT '分区',
    `create_time`    datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='odps元数据质量表';



-- 自定义函数 脱水印
-- 后续需要处理特殊字符 拉丁语 [PDF] [LRO] unicode
-- https://www.unicode.org/mail-arch/unicode-ml/Archives-Old/UML020/0049.html
-- http://mysql.rjweb.org/doc.php/charcoll#fixes_for_various_cases
-- https://blog.csdn.net/u011649691/article/details/109242186
-- https://r12a.github.io/app-conversion/index8-2
-- https://tool.chinaz.com/tools/unicode.aspx
-- https://blog.csdn.net/candyguy242/article/details/8476231
-- https://blog.csdn.net/weixin_35562134/article/details/113589352
-- UNHEX(replace(replace(hex(tbl_comment), 'E280AC', ''), 'E280AD', ''))
-- replace(replace(tbl_comment, CHAR(14844077), ''), char(14844076), '')
drop function if exists UPDATE_WATERMARKING;
create function UPDATE_WATERMARKING(sourceData varchar(255)) returns varchar(255)
    return UNHEX(replace(replace(hex(sourceData), 'E280AC', ''), 'E280AD', ''));


-- 删除存储过程
DROP PROCEDURE IF EXISTS `pro_update_gfdn`;
DROP PROCEDURE IF EXISTS `pro_update_met_detail_outline`;
DROP PROCEDURE IF EXISTS `pro_update_met_blood_relation_detail`;
DROP PROCEDURE IF EXISTS `pro_update_met_quality`;

-- 封装每日存储过程(共富大脑仓)
delimiter $$
CREATE PROCEDURE pro_update_gfdn()
BEGIN
    SET @dwID = 1;
    call pro_update_met_detail_outline(@dwID);
    call pro_update_met_blood_relation_detail(@dwID);
    call pro_update_met_quality(@dwID);
END $$

-- 存储过程更新元数据宽表
CREATE PROCEDURE pro_update_met_detail_outline(IN dw_id INTEGER)
BEGIN
    SET @dwID = dw_id;
    DELETE
    FROM backstage.met_detail_outline
    WHERE dw_id = @dwID;
    INSERT INTO backstage.met_detail_outline
    (DW_ID, TBL_LEVEL, TBL_NAME, TBL_COMMENT, COL_NAME, COL_TYPE, COL_COMMENT, TBL_COL_NUM, ROW_NUM, INDEX1_NAME,
     INDEX2_NAME,
     INDEX3_NAME, IS_PT, PT_LAST, TBL_CREATE_TIME, TBL_UPDATE_TIME, TBL_LIFECYCLE, TBL_TYPE, TBL_SIZE, ETL_TM, DT)
    SELECT @dwID,
           UPDATE_WATERMARKING(TBL_LEVEL),
           UPDATE_WATERMARKING(TBL_NAME),
           UPDATE_WATERMARKING(TBL_COMMENT),
           UPDATE_WATERMARKING(COL_NAME),
           UPDATE_WATERMARKING(COL_TYPE),
           UPDATE_WATERMARKING(COL_COMMENT),
           UPDATE_WATERMARKING(TBL_COL_NUM),
           -- TODO 后续从ODPS获取行数,实际每个表的row_num 相同
           RAND() * 10000 as ROW_NUM,
           UPDATE_WATERMARKING(INDEX1_NAME),
           UPDATE_WATERMARKING(INDEX2_NAME),
           UPDATE_WATERMARKING(INDEX3_NAME),
           UPDATE_WATERMARKING(IS_PT),
           UPDATE_WATERMARKING(PT_LAST),
           UPDATE_WATERMARKING(TBL_CREATE_TIME),
           UPDATE_WATERMARKING(TBL_UPDATE_TIME),
           UPDATE_WATERMARKING(TBL_LIFECYCLE),
           UPDATE_WATERMARKING(TBL_TYPE),
           UPDATE_WATERMARKING(TBL_SIZE),
           UPDATE_WATERMARKING(ETL_TM),
           UPDATE_WATERMARKING(DT)
    from gfdn_odps_export.dim_gfdn_meta_detail_outline;
END $$

-- 存储过程更新数据血缘
CREATE PROCEDURE pro_update_met_blood_relation_detail(IN dw_id INTEGER)
BEGIN
    SET @dwID = dw_id;
    DELETE
    FROM backstage.met_blood_relation_detail
    WHERE dw_id = @dwID;
    INSERT INTO backstage.met_blood_relation_detail
    (dw_id, ins_name, tbl_out_en, tbl_out_zh, tbl_in_en, tbl_in_zh, etl_tm, dt)
    SELECT @dwID,
           UPDATE_WATERMARKING(ins_name),
           UPDATE_WATERMARKING(tbl_out_en),
           UPDATE_WATERMARKING(tbl_out_zh),
           UPDATE_WATERMARKING(tbl_in_en),
           UPDATE_WATERMARKING(tbl_in_zh),
           UPDATE_WATERMARKING(etl_tm),
           UPDATE_WATERMARKING(dt)
    from gfdn_odps_export.dim_gfdn_meta_blood_relation_detail;
END $$

-- 存储过程更新数据质量
CREATE PROCEDURE pro_update_met_quality(IN dw_id INTEGER)
BEGIN
    SET @dwID = dw_id;
    DELETE
    FROM backstage.met_quality
    WHERE dw_id = @dwID;
    INSERT INTO backstage.met_quality
    (dw_id, table_name, table_comment, col_name, col_comment, distinct_count, total_count, max_len, min_len, max_value,
     min_value, null_count, null_rate, spend_time, etl_tm, dt)
    select @dwID,
           UPDATE_WATERMARKING(table_name),
           UPDATE_WATERMARKING(table_comment),
           UPDATE_WATERMARKING(col_name),
           UPDATE_WATERMARKING(col_comment),
           UPDATE_WATERMARKING(distinct_count),
           UPDATE_WATERMARKING(total_count),
           UPDATE_WATERMARKING(max_len),
           UPDATE_WATERMARKING(min_len),
           UPDATE_WATERMARKING(max_value),
           UPDATE_WATERMARKING(min_value),
           UPDATE_WATERMARKING(null_count),
           UPDATE_WATERMARKING(null_rate),
           UPDATE_WATERMARKING(spend_time),
           UPDATE_WATERMARKING(etl_tm),
           UPDATE_WATERMARKING(dt)
    from gfdn_odps_export.dim_gfdn_meta_quality;
END $$


delimiter ;

-- 调用存储过程（共富大脑仓）
CALL pro_update_gfdn();

-- 查看定时器是否开启
show variables like '%event_sche%';
-- 当OFF,需要开启定时器
set global event_scheduler=1;
-- 查看现有的定时器
show events;

-- 定时器 每日早上7点执行
DROP EVENT IF EXISTS event_update_gfdn ;
CREATE EVENT `event_update_gfdn`
    -- 每隔一天执行一次，开始执行时间为明天7点整
    ON SCHEDULE EVERY 1 DAY STARTS DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 7 HOUR)
    ON COMPLETION NOT PRESERVE
    ENABLE
    DO call pro_update_gfdn()	-- 指定要执行的存储过程
;
