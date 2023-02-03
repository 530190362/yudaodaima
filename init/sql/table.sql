use backstage;


-- 表设计

-- 表前缀模块
# ums 权限模块
# met 数仓元数据模块

--
drop table if exists `met_detail_outline`;
CREATE TABLE if not exists `met_detail_outline`
(
    `id`              int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_id`           int(10)      DEFAULT NULL COMMENT '数仓id',
    `tbl_level`       varchar(255) DEFAULT NULL COMMENT '表层级',
    `tbl_name`        varchar(255) DEFAULT NULL COMMENT '表英文名',
    `tbl_comment`     varchar(255) DEFAULT NULL COMMENT '表中文名',
    `col_name`        varchar(255) DEFAULT NULL COMMENT '字段名',
    `col_type`        varchar(255) DEFAULT NULL COMMENT '字段类型',
    `col_comment`     varchar(255) DEFAULT NULL COMMENT '字段备注',
    `tbl_col_num`     varchar(255)  DEFAULT NULL COMMENT '字段数',
    `index1_name`     varchar(255) DEFAULT NULL COMMENT '表一级明细',
    `index2_name`     varchar(255) DEFAULT NULL COMMENT '表二级明细',
    `index3_name`     varchar(255) DEFAULT NULL COMMENT '表三级明细',
    `is_pt`           varchar(255)   DEFAULT NULL COMMENT '是否是分区表 1-是 0-否',
    `pt_last`         varchar(255) DEFAULT NULL COMMENT '最新分区',
    `tbl_create_time` varchar(255) DEFAULT NULL COMMENT '表创建时间',
    `tbl_update_time` varchar(255) DEFAULT NULL COMMENT '表更新时间',
    `tbl_lifecycle`   varchar(255) DEFAULT NULL COMMENT '表生命周期',
    `tbl_type`        varchar(255) DEFAULT NULL COMMENT '表类型 内部表/外部表',
    `tbl_size`        varchar(255) DEFAULT NULL COMMENT '表大小 单位:MB',
    `etl_tm`          varchar(255) DEFAULT NULL COMMENT 'ETL时间',
    `dt`              varchar(255) DEFAULT NULL COMMENT '分区',
    `create_time`     datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='ODPS元数据宽表';




-- 删除存储过程
DROP PROCEDURE IF EXISTS `pro_update_met_detail_outline`;

-- 创建存储过程
delimiter $$
CREATE PROCEDURE  pro_update_met_detail_outline(IN dw_id INTEGER)
BEGIN
    SET @dwID= dw_id;
    DELETE
    FROM backstage.met_detail_outline
    WHERE dw_id = @dwID;
    INSERT INTO backstage.met_detail_outline
    (DW_ID, TBL_LEVEL, TBL_NAME, TBL_COMMENT, COL_NAME, COL_TYPE, COL_COMMENT, TBL_COL_NUM, INDEX1_NAME, INDEX2_NAME, INDEX3_NAME, IS_PT, PT_LAST, TBL_CREATE_TIME, TBL_UPDATE_TIME, TBL_LIFECYCLE, TBL_TYPE, TBL_SIZE, ETL_TM, DT)
    SELECT @dwID, TBL_LEVEL, TBL_NAME, TBL_COMMENT, COL_NAME, COL_TYPE, COL_COMMENT, TBL_COL_NUM, INDEX1_NAME, INDEX2_NAME, INDEX3_NAME, IS_PT, PT_LAST, TBL_CREATE_TIME, TBL_UPDATE_TIME, TBL_LIFECYCLE, TBL_TYPE, TBL_SIZE, ETL_TM, DT
    from gfdn_odps_export.dim_gfdn_meta_detail_outline;
END $$


delimiter ;

-- 调用存储过程
CALL pro_update_met_detail_outline(1);


-- 后续需要处理特殊字符 拉丁语 [PDF] [LRO] unicode
-- https://www.unicode.org/mail-arch/unicode-ml/Archives-Old/UML020/0049.html
-- http://mysql.rjweb.org/doc.php/charcoll#fixes_for_various_cases
-- https://blog.csdn.net/u011649691/article/details/109242186
-- https://r12a.github.io/app-conversion/index8-2
-- https://tool.chinaz.com/tools/unicode.aspx
-- https://blog.csdn.net/candyguy242/article/details/8476231
-- https://blog.csdn.net/weixin_35562134/article/details/113589352

-- 脱水印方法
select tbl_comment as c1
     ,HEX(tbl_comment) as c2
     ,UNHEX(replace(replace(hex(tbl_comment),'E280AC',''),'E280AD','')) as c3
     ,replace(replace(tbl_comment, CHAR(14844077),''),char(14844076),'') as c4
     ,UNHEX(HEX(tbl_comment)) as c5
from met_detail_outline;



