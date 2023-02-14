/*
 Navicat Premium Data Transfer

 Source Server         : hadoop103
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : hadoop103:9003
 Source Schema         : backstage

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 14/02/2023 10:21:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ass_solution_exp
-- ----------------------------
DROP TABLE IF EXISTS `ass_solution_exp`;
CREATE TABLE `ass_solution_exp`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `solution_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '解决方案名称',
  `solution_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解决方案描述',
  `solution_dingding_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解决方案钉钉URL',
  `solution_gitlab_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解决方案GitLabURL',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '解决方案落地经验库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for met_data_column
-- ----------------------------
DROP TABLE IF EXISTS `met_data_column`;
CREATE TABLE `met_data_column`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NOT NULL COMMENT '数仓ID',
  `tbl_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `col_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `col_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段类型',
  `col_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tbl_name`(`tbl_name`) USING BTREE,
  INDEX `idx_col_name`(`col_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16384 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产-ODPS表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_data_label
-- ----------------------------
DROP TABLE IF EXISTS `met_data_label`;
CREATE TABLE `met_data_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `label_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `label_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签备注',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联表名',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除  0:未删除 1:已删除',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for met_data_overview
-- ----------------------------
DROP TABLE IF EXISTS `met_data_overview`;
CREATE TABLE `met_data_overview`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NULL DEFAULT NULL COMMENT '数仓id',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `tbl_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表层级',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `table_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `tbl_col_num` int(10) NULL DEFAULT NULL COMMENT '字段数',
  `tbl_create_time` datetime NULL DEFAULT NULL COMMENT '表创建时间',
  `tbl_update_time` datetime NULL DEFAULT NULL COMMENT '表更新时间',
  `tbl_size` decimal(16, 2) NULL DEFAULT NULL COMMENT '表大小 单位:MB',
  `is_pt` tinyint(1) NULL DEFAULT NULL COMMENT '是否是分区表 1-是 0-否',
  `index1_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表一级明细',
  `index2_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表二级明细',
  `index3_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表三级明细',
  `tbl_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表类型 内部表/外部表',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除  0:未删除 1:已删除',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `tbl_lifecycle` int(10) NULL DEFAULT NULL COMMENT '表生命周期',
  `pt_last` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最新分区',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产概览表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_data_overview_label_relation
-- ----------------------------
DROP TABLE IF EXISTS `met_data_overview_label_relation`;
CREATE TABLE `met_data_overview_label_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `overview_id` int(10) NULL DEFAULT NULL COMMENT '数据资产id',
  `label_id` int(10) NULL DEFAULT NULL COMMENT '标签id',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除  0:未删除 1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产标签映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_data_sync_log
-- ----------------------------
DROP TABLE IF EXISTS `met_data_sync_log`;
CREATE TABLE `met_data_sync_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '同步名称',
  `spend_times` int(5) NOT NULL COMMENT '花费时间单位 : 秒',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产-数据同步日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_data_table
-- ----------------------------
DROP TABLE IF EXISTS `met_data_table`;
CREATE TABLE `met_data_table`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NOT NULL COMMENT '数仓ID',
  `tbl_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表层级',
  `index1_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表一级明细',
  `index2_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表二级明细',
  `index3_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表三级明细',
  `tbl_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `tbl_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表备注',
  `tbl_create_time` datetime NOT NULL COMMENT '表创建日期',
  `tbl_update_time` datetime NOT NULL COMMENT '表更新日期',
  `is_pt` tinyint(1) NULL DEFAULT NULL COMMENT '是否是分区表 1-是 0-否',
  `pt_last` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最新分区',
  `tbl_lifecycle` int(10) NULL DEFAULT NULL COMMENT '表生命周期',
  `tbl_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表类型 内部表/外部表',
  `tbl_size` decimal(16, 2) NULL DEFAULT NULL COMMENT '表大小 单位:MB',
  `tbl_col_num` int(10) NULL DEFAULT NULL COMMENT '字段数',
  `all_row_count` int(10) NULL DEFAULT NULL COMMENT '总行数',
  `row_count` int(10) NULL DEFAULT NULL COMMENT '最新分区行数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tbl_name`(`tbl_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1024 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据资产-ODPS表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_dw_info
-- ----------------------------
DROP TABLE IF EXISTS `met_dw_info`;
CREATE TABLE `met_dw_info`  (
  `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数仓名称(英文)',
  `dw_name_zn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数仓名称(中文)',
  `dw_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数仓描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数仓主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_explore_report
-- ----------------------------
DROP TABLE IF EXISTS `met_explore_report`;
CREATE TABLE `met_explore_report`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(5) NOT NULL COMMENT '数仓ID',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `table_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表描述',
  `col_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `col_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段类型',
  `col_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段描述',
  `distinct_count` bigint(10) NULL DEFAULT NULL COMMENT '去重记录数',
  `total_count` bigint(10) NULL DEFAULT NULL COMMENT '总记录数(最近的分区)',
  `max_len` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最大长度',
  `min_len` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最小长度',
  `max_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '最大值',
  `min_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '最小值',
  `null_count` bigint(10) NULL DEFAULT NULL COMMENT '空值数',
  `null_rate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '空值率',
  `value_kind_json` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值种类(所占百分比,超过15个不显示),JSON格式化',
  `is_only_value` tinyint(1) NULL DEFAULT 0 COMMENT '是否只有一个值',
  `spend_time` int(5) NULL DEFAULT NULL COMMENT '读取记录数花费的时间单位:秒',
  `etl_tm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '探查时间',
  `dt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日期',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据勘探-数据勘探报告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_explore_task
-- ----------------------------
DROP TABLE IF EXISTS `met_explore_task`;
CREATE TABLE `met_explore_task`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tbl_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '勘探的表名',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_date` date NULL DEFAULT NULL COMMENT '创建日期',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据勘探-数据勘探操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_norm_dict
-- ----------------------------
DROP TABLE IF EXISTS `met_norm_dict`;
CREATE TABLE `met_norm_dict`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '上级字典名称id',
  `dict_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT ',' COMMENT '树结构',
  `sort_value` int(11) NULL DEFAULT 1 COMMENT '排序',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1正常 0停用）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据标准-字典库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_norm_node
-- ----------------------------
DROP TABLE IF EXISTS `met_norm_node`;
CREATE TABLE `met_norm_node`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点类型/名称',
  `rule` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点命名规范',
  `node_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点描述',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据标准-任务节点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_norm_root
-- ----------------------------
DROP TABLE IF EXISTS `met_norm_root`;
CREATE TABLE `met_norm_root`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词根中文',
  `root_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '词根描述',
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词根英文',
  `name_short_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词根英文简写',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据标准-字根库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_norm_table
-- ----------------------------
DROP TABLE IF EXISTS `met_norm_table`;
CREATE TABLE `met_norm_table`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小层层级名称',
  `rule` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '命名标准版',
  `lifecycle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生命周期',
  `table_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `example_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '示例中文名称',
  `example_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '示例英文名称',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据标准-表命名规范' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_quality_rule
-- ----------------------------
DROP TABLE IF EXISTS `met_quality_rule`;
CREATE TABLE `met_quality_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NULL DEFAULT NULL COMMENT '数仓id',
  `rule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `rule_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则描述',
  `rule_type` int(10) NULL DEFAULT 0 COMMENT '规则类型',
  `rule_bind_num` int(10) NULL DEFAULT 0 COMMENT '绑定任务数',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据质量-质检规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_quality_rule_task_relation
-- ----------------------------
DROP TABLE IF EXISTS `met_quality_rule_task_relation`;
CREATE TABLE `met_quality_rule_task_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `rule_id` int(10) NULL DEFAULT NULL COMMENT '规则id',
  `task_id` int(10) NULL DEFAULT NULL COMMENT '任务id',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除  0:未删除 1:已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '质检规则任务映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_quality_task
-- ----------------------------
DROP TABLE IF EXISTS `met_quality_task`;
CREATE TABLE `met_quality_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NULL DEFAULT NULL COMMENT '数仓id',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `monitor_num` int(10) NULL DEFAULT 0 COMMENT '监测次数',
  `warn_num` int(10) NULL DEFAULT 0 COMMENT '预警次数',
  `rule_id` int(10) NULL DEFAULT NULL COMMENT '绑定规则',
  `bind_tbl` int(10) NULL DEFAULT NULL COMMENT '绑定表',
  `bind_col` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '绑定字段',
  `target_begin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标范围起',
  `target_end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标范围始',
  `monitor_freq` int(10) NULL DEFAULT 0 COMMENT '监测频率',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态 1-上线 0-下线',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据质量-质检任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_quality_warn
-- ----------------------------
DROP TABLE IF EXISTS `met_quality_warn`;
CREATE TABLE `met_quality_warn`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dw_id` int(10) NULL DEFAULT NULL COMMENT '数仓id',
  `task_id` int(10) NULL DEFAULT NULL COMMENT '任务名称',
  `rule_type` int(10) NULL DEFAULT NULL COMMENT '规则类型',
  `target_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标范围',
  `actual_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实际值',
  `warn_time` datetime NULL DEFAULT NULL COMMENT '预警时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据质量-监测预警' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for met_res_function
-- ----------------------------
DROP TABLE IF EXISTS `met_res_function`;
CREATE TABLE `met_res_function`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '函数英文名称',
  `name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '函数中文名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类 UDF/UDAF/UDTF',
  `class_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类名',
  `jar_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源列表',
  `function_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `command` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '命令格式',
  `param_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数说明',
  `return_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返回值',
  `example` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '示例',
  `is_company` tinyint(1) NULL DEFAULT 1 COMMENT '函数来源 1-公司创建的函数 0-系统自带函数',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源管理-UDF函数管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_agent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 480 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) NULL DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) NULL DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) NULL DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 728 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NULL DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 216 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for view_explore_data_column
-- ----------------------------
DROP VIEW IF EXISTS `view_explore_data_column`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_explore_data_column` AS select `met_explore_report`.`dw_id` AS `dw_id`,`met_explore_report`.`table_name` AS `table_name`,`met_explore_report`.`col_name` AS `col_name`,`met_explore_report`.`col_type` AS `col_type`,`met_explore_report`.`col_comment` AS `col_comment`,`met_explore_report`.`max_len` AS `max_len`,`met_explore_report`.`min_len` AS `min_len`,`met_explore_report`.`max_value` AS `max_value`,`met_explore_report`.`min_value` AS `min_value`,`met_explore_report`.`null_count` AS `null_count`,`met_explore_report`.`null_rate` AS `null_rate`,`met_explore_report`.`value_kind_json` AS `value_kind_json`,`met_explore_report`.`is_only_value` AS `is_only_value` from `met_explore_report`;

-- ----------------------------
-- View structure for view_explore_data_table
-- ----------------------------
DROP VIEW IF EXISTS `view_explore_data_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_explore_data_table` AS select `met_explore_report`.`dw_id` AS `dw_id`,`met_explore_report`.`table_name` AS `table_name`,`met_explore_report`.`table_comment` AS `table_comment`,sum(`met_explore_report`.`distinct_count`) AS `distinct_count`,sum(`met_explore_report`.`total_count`) AS `total_count`,max(`met_explore_report`.`spend_time`) AS `spend_time`,min(`met_explore_report`.`etl_tm`) AS `etl_tm` from `met_explore_report` group by `met_explore_report`.`dw_id`,`met_explore_report`.`table_name`,`met_explore_report`.`table_comment`;

-- ----------------------------
-- View structure for view_met_blood_relation_detail
-- ----------------------------
DROP VIEW IF EXISTS `view_met_blood_relation_detail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_met_blood_relation_detail` AS select `gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`ins_name` AS `ins_name`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`tbl_out_en` AS `tbl_out_en`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`tbl_out_zh` AS `tbl_out_zh`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`tbl_in_en` AS `tbl_in_en`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`tbl_in_zh` AS `tbl_in_zh`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`etl_tm` AS `etl_tm`,`gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`.`dt` AS `dt` from `gfdn_odps_export`.`dim_gfdn_meta_blood_relation_detail`;

-- ----------------------------
-- View structure for view_met_detail_outline
-- ----------------------------
DROP VIEW IF EXISTS `view_met_detail_outline`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_met_detail_outline` AS select `gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_level` AS `tbl_level`,if((`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_level` = 'ods'),if(((md5(`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_name`) % 99) = 1),concat('ods_bms_',`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_name`),concat('ods_irs_',`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_name`)),`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_name`) AS `tbl_name`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_comment` AS `tbl_comment`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`col_name` AS `col_name`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`col_type` AS `col_type`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`col_comment` AS `col_comment`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_col_num` AS `tbl_col_num`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`all_row_count` AS `all_row_count`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`row_count` AS `row_count`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`index1_name` AS `index1_name`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`index2_name` AS `index2_name`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`index3_name` AS `index3_name`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`is_pt` AS `is_pt`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`pt_last` AS `pt_last`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_create_time` AS `tbl_create_time`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_update_time` AS `tbl_update_time`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_lifecycle` AS `tbl_lifecycle`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_type` AS `tbl_type`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`tbl_size` AS `tbl_size`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`etl_tm` AS `etl_tm`,`gfdn_odps_export`.`dim_gfdn_meta_detail_outline`.`dt` AS `dt` from `gfdn_odps_export`.`dim_gfdn_meta_detail_outline`;


-- ----------------------------
-- View structure for view_met_data_column
-- ----------------------------
DROP VIEW IF EXISTS `view_met_data_column`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_met_data_column` AS select `t`.`tbl_name` AS `tbl_name`,`t`.`col_name` AS `col_name`,`t`.`col_type` AS `col_type`,`t`.`col_comment` AS `col_comment` from (select `view_met_detail_outline`.`tbl_name` AS `tbl_name`,`view_met_detail_outline`.`col_name` AS `col_name`,`view_met_detail_outline`.`col_type` AS `col_type`,`view_met_detail_outline`.`col_comment` AS `col_comment` from `backstage`.`view_met_detail_outline` where (`view_met_detail_outline`.`dt` = (select max(`view_met_detail_outline`.`dt`) from `backstage`.`view_met_detail_outline`))) `t` group by `t`.`tbl_name`,`t`.`col_name`,`t`.`col_type`,`t`.`col_comment`;

-- ----------------------------
-- View structure for view_met_data_table
-- ----------------------------
DROP VIEW IF EXISTS `view_met_data_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_met_data_table` AS select `t`.`tbl_name` AS `tbl_name`,max(`t`.`tbl_level`) AS `tbl_level`,max(`t`.`index1_name`) AS `index1_name`,max(`t`.`index2_name`) AS `index2_name`,max(`t`.`index3_name`) AS `index3_name`,max(`t`.`tbl_comment`) AS `tbl_comment`,max(`t`.`tbl_create_time`) AS `tbl_create_time`,max(`t`.`tbl_update_time`) AS `tbl_update_time`,max(`t`.`is_pt`) AS `is_pt`,max(`t`.`pt_last`) AS `pt_last`,max(`t`.`tbl_lifecycle`) AS `tbl_lifecycle`,max(`t`.`tbl_type`) AS `tbl_type`,max(`t`.`tbl_size`) AS `tbl_size`,max(`t`.`tbl_col_num`) AS `tbl_col_num`,max(`t`.`all_row_count`) AS `all_row_count`,max(`t`.`row_count`) AS `row_count` from (select `view_met_detail_outline`.`tbl_name` AS `tbl_name`,`view_met_detail_outline`.`tbl_level` AS `tbl_level`,`view_met_detail_outline`.`index1_name` AS `index1_name`,`view_met_detail_outline`.`index2_name` AS `index2_name`,`view_met_detail_outline`.`index3_name` AS `index3_name`,`view_met_detail_outline`.`tbl_comment` AS `tbl_comment`,`view_met_detail_outline`.`tbl_create_time` AS `tbl_create_time`,`view_met_detail_outline`.`tbl_update_time` AS `tbl_update_time`,`view_met_detail_outline`.`tbl_size` AS `tbl_size`,`view_met_detail_outline`.`is_pt` AS `is_pt`,`view_met_detail_outline`.`tbl_type` AS `tbl_type`,`view_met_detail_outline`.`tbl_lifecycle` AS `tbl_lifecycle`,`view_met_detail_outline`.`pt_last` AS `pt_last`,`view_met_detail_outline`.`tbl_col_num` AS `tbl_col_num`,`view_met_detail_outline`.`all_row_count` AS `all_row_count`,`view_met_detail_outline`.`row_count` AS `row_count` from `backstage`.`view_met_detail_outline` where (`view_met_detail_outline`.`dt` = (select max(`view_met_detail_outline`.`dt`) from `backstage`.`view_met_detail_outline`))) `t` group by `t`.`tbl_name`;

-- ----------------------------
-- ----------------------------
-- View structure for view_met_quality
-- ----------------------------
DROP VIEW IF EXISTS `view_met_quality`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_met_quality` AS select `gfdn_odps_export`.`dim_gfdn_meta_quality`.`table_name` AS `table_name`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`table_comment` AS `table_comment`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`col_name` AS `col_name`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`col_comment` AS `col_comment`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`distinct_count` AS `distinct_count`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`total_count` AS `total_count`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`max_len` AS `max_len`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`min_len` AS `min_len`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`max_value` AS `max_value`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`min_value` AS `min_value`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`null_count` AS `null_count`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`null_rate` AS `null_rate`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`spend_time` AS `spend_time`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`etl_tm` AS `etl_tm`,`gfdn_odps_export`.`dim_gfdn_meta_quality`.`dt` AS `dt` from `gfdn_odps_export`.`dim_gfdn_meta_quality`;

SET FOREIGN_KEY_CHECKS = 1;
