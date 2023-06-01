drop table if exists met_dw_table_map_info;
CREATE TABLE `met_dw_table_map_info`
(
    `id`                             int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `dw_name`                        varchar(50) NOT NULL COMMENT '数仓名称',
    `map_meta_detail_outline`        varchar(50) NOT NULL COMMENT '映射元数据明细表表名',
    `map_meta_blood_relation_detail` varchar(50) NOT NULL COMMENT '映射元数据血缘表表名',
    `map_meta_quality`               varchar(50) NOT NULL COMMENT '映射元数据质量表表名',
    `create_time`                    datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`                    datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`                     tinyint(1)  DEFAULT '0' COMMENT '是否删除 1-是 0-否',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC COMMENT ='数仓表映射表';

