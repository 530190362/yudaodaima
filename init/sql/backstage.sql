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

 Date: 03/02/2023 09:42:19
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
-- Records of ass_solution_exp
-- ----------------------------
INSERT INTO `ass_solution_exp` VALUES (1, '元数据mysql数据导出', '基于shell+java自定义多数据源,数据导出成excel', NULL, 'http://extgitlab.ytbig.cn:8011/homjay666/Export-Meta.git', '2023-02-01 05:48:18', '2023-02-01 05:48:18', 0);
INSERT INTO `ass_solution_exp` VALUES (2, 'mysql数据备份', '基于shell+python+mysql数据每日自定义导出', NULL, 'http://extgitlab.ytbig.cn:8011/homjay666/Backup-Mysql.git', '2023-02-01 05:48:18', '2023-02-01 05:48:18', 0);
INSERT INTO `ass_solution_exp` VALUES (3, 'mysql数据同步', '基于shell+dataX每日数据同步', NULL, 'http://extgitlab.ytbig.cn:8011/bigdata-group/Prosperity-Echo-Data-Task.git', '2023-02-01 05:48:18', '2023-02-01 05:48:18', 0);
INSERT INTO `ass_solution_exp` VALUES (4, 'string', 'string', 'string', 'string', '2023-02-01 05:49:03', '2023-02-01 05:49:19', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (3, 'admin', '$2a$10$kbw6Oz9o4uMZ.NUryp7wmuW.HeGls6gYNh/ltetL2KOxQHArI8xO.', 'http://gravatar.net-r-studio.top/', 'admin@163.com', '系统管理员', '系统管理员', '2022-06-01 13:32:47', '2023-02-04 12:45:16', 1);
INSERT INTO `ums_admin` VALUES (16, 'admin1', '$2a$10$kbw6Oz9o4uMZ.NUryp7wmuW.HeGls6gYNh/ltetL2KOxQHArI8xO.', 'http://gravatar.net-r-studio.top/', 'admin@163.com', '小A', '数仓开发工程师', '2023-01-31 11:53:32', '2023-02-04 12:45:16', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 346 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_login_log
-- ----------------------------
INSERT INTO `ums_admin_login_log` VALUES (285, 3, '2020-08-24 14:05:21', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (286, 10, '2020-08-24 14:05:39', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (287, 3, '2023-01-31 11:20:48', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (288, 3, '2023-01-31 11:54:03', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (289, 16, '2023-01-31 11:54:33', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (290, 16, '2023-01-31 11:54:38', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (291, 16, '2023-01-31 11:54:48', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (292, 3, '2023-01-31 11:54:53', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (293, 16, '2023-01-31 11:56:02', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (294, 3, '2023-01-31 11:56:18', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (295, 3, '2023-01-31 14:53:54', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (296, 3, '2023-01-31 21:13:16', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (297, 3, '2023-01-31 21:35:44', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (298, 3, '2023-01-31 21:36:06', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (299, 3, '2023-01-31 21:36:34', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (300, 16, '2023-01-31 21:52:23', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (301, 16, '2023-01-31 21:53:10', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (302, 3, '2023-01-31 21:53:24', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (303, 3, '2023-01-31 21:57:50', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (304, 16, '2023-01-31 22:03:34', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (305, 3, '2023-01-31 22:03:45', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (306, 3, '2023-01-31 22:14:50', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (307, 3, '2023-01-31 22:22:10', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (308, 3, '2023-01-31 22:22:28', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (309, 3, '2023-01-31 22:22:59', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (310, 3, '2023-02-01 10:46:43', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (311, 3, '2023-02-01 11:03:54', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (312, 3, '2023-02-01 14:16:51', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (313, 3, '2023-02-01 15:17:05', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (314, 3, '2023-02-01 15:32:00', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (315, 3, '2023-02-01 15:43:43', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (316, 3, '2023-02-01 16:10:44', '211.90.249.138', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (317, 3, '2023-02-01 16:24:30', '211.90.249.138', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (318, 3, '2023-02-01 16:27:00', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (319, 3, '2023-02-01 16:33:01', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (320, 3, '2023-02-01 16:34:15', '39.170.57.63', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (321, 3, '2023-02-01 16:34:55', '39.170.57.63', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (322, 3, '2023-02-01 16:38:51', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (323, 3, '2023-02-01 16:43:17', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (324, 3, '2023-02-01 17:18:50', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (325, 3, '2023-02-01 17:18:56', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (326, 3, '2023-02-01 17:27:33', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (327, 3, '2023-02-01 17:27:45', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (328, 3, '2023-02-02 08:46:59', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (329, 3, '2023-02-02 08:47:10', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (330, 3, '2023-02-02 09:55:52', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (331, 3, '2023-02-02 10:56:17', '39.170.57.63', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (332, 3, '2023-02-02 11:09:38', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (333, 16, '2023-02-02 11:51:35', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (334, 3, '2023-02-02 11:52:08', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (335, 3, '2023-02-02 16:07:42', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (336, 16, '2023-02-02 16:19:06', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (337, 3, '2023-02-02 16:19:25', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (338, 3, '2023-02-02 16:19:28', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (339, 3, '2023-02-02 16:28:50', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (340, 16, '2023-02-02 17:31:55', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (341, 16, '2023-02-02 17:31:55', '220.191.233.79', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (342, 16, '2023-02-03 08:57:56', '211.90.249.69', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (343, 3, '2023-02-03 08:58:04', '211.90.249.69', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (344, 3, '2023-02-03 09:36:03', '39.170.57.63', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (345, 3, '2023-02-03 09:36:19', '39.170.57.63', NULL, NULL);

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES (26, 3, 5);
INSERT INTO `ums_admin_role_relation` VALUES (27, 6, 1);
INSERT INTO `ums_admin_role_relation` VALUES (28, 7, 2);
INSERT INTO `ums_admin_role_relation` VALUES (29, 1, 5);
INSERT INTO `ums_admin_role_relation` VALUES (30, 4, 5);
INSERT INTO `ums_admin_role_relation` VALUES (31, 8, 5);
INSERT INTO `ums_admin_role_relation` VALUES (34, 12, 6);
INSERT INTO `ums_admin_role_relation` VALUES (38, 13, 5);
INSERT INTO `ums_admin_role_relation` VALUES (39, 10, 8);
INSERT INTO `ums_admin_role_relation` VALUES (41, 16, 9);
INSERT INTO `ums_admin_role_relation` VALUES (42, 16, 10);

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (2, 1, '2020-02-02 14:51:50', '商品列表', 1, 0, 'product', 'product-list', 0);
INSERT INTO `ums_menu` VALUES (3, 1, '2020-02-02 14:52:44', '添加商品', 1, 0, 'addProduct', 'product-add', 0);
INSERT INTO `ums_menu` VALUES (4, 1, '2020-02-02 14:53:51', '商品分类', 1, 0, 'productCate', 'product-cate', 0);
INSERT INTO `ums_menu` VALUES (5, 1, '2020-02-02 14:54:51', '商品类型', 1, 0, 'productAttr', 'product-attr', 0);
INSERT INTO `ums_menu` VALUES (6, 1, '2020-02-02 14:56:29', '品牌管理', 1, 0, 'brand', 'product-brand', 0);
INSERT INTO `ums_menu` VALUES (8, 7, '2020-02-02 16:55:18', '订单列表', 1, 0, 'order', 'product-list', 0);
INSERT INTO `ums_menu` VALUES (9, 7, '2020-02-02 16:56:46', '订单设置', 1, 0, 'orderSetting', 'order-setting', 0);
INSERT INTO `ums_menu` VALUES (10, 7, '2020-02-02 16:57:39', '退货申请处理', 1, 0, 'returnApply', 'order-return', 0);
INSERT INTO `ums_menu` VALUES (11, 7, '2020-02-02 16:59:40', '退货原因设置', 1, 0, 'returnReason', 'order-return-reason', 0);
INSERT INTO `ums_menu` VALUES (13, 12, '2020-02-04 16:19:22', '秒杀活动列表', 1, 0, 'flash', 'sms-flash', 0);
INSERT INTO `ums_menu` VALUES (14, 12, '2020-02-04 16:20:16', '优惠券列表', 1, 0, 'coupon', 'sms-coupon', 0);
INSERT INTO `ums_menu` VALUES (16, 12, '2020-02-07 16:22:38', '品牌推荐', 1, 0, 'homeBrand', 'product-brand', 0);
INSERT INTO `ums_menu` VALUES (17, 12, '2020-02-07 16:23:14', '新品推荐', 1, 0, 'homeNew', 'sms-new', 0);
INSERT INTO `ums_menu` VALUES (18, 12, '2020-02-07 16:26:38', '人气推荐', 1, 0, 'homeHot', 'sms-hot', 0);
INSERT INTO `ums_menu` VALUES (19, 12, '2020-02-07 16:28:16', '专题推荐', 1, 0, 'homeSubject', 'sms-subject', 0);
INSERT INTO `ums_menu` VALUES (20, 12, '2020-02-07 16:28:42', '广告列表', 1, 0, 'homeAdvertise', 'sms-ad', 0);
INSERT INTO `ums_menu` VALUES (21, 0, '2020-02-07 16:29:13', '权限模块', 0, 0, 'ums', 'ums', 0);
INSERT INTO `ums_menu` VALUES (22, 21, '2020-02-07 16:29:51', '用户列表', 1, 0, 'admin', 'ums-admin', 0);
INSERT INTO `ums_menu` VALUES (23, 21, '2020-02-07 16:30:13', '角色列表', 1, 0, 'role', 'ums-role', 0);
INSERT INTO `ums_menu` VALUES (24, 21, '2020-02-07 16:30:53', '菜单列表', 1, 0, 'menu', 'ums-menu', 0);
INSERT INTO `ums_menu` VALUES (25, 21, '2020-02-07 16:31:13', '资源列表', 1, 0, 'resource', 'ums-resource', 0);
INSERT INTO `ums_menu` VALUES (30, 29, '2023-01-31 11:21:57', '数据可视化', 1, 0, 'main', 'ums', 0);
INSERT INTO `ums_menu` VALUES (31, 0, '2023-01-31 21:38:22', '数仓开发模块', 0, 3, 'dw', 'dw', 0);
INSERT INTO `ums_menu` VALUES (32, 31, '2023-01-31 21:38:38', '数据血缘', 1, 0, 'bloodRelationship', 'dw-bloodRelationship', 0);
INSERT INTO `ums_menu` VALUES (33, 31, '2023-01-31 21:38:51', '数据开发', 1, 0, 'development', 'dw-development', 0);
INSERT INTO `ums_menu` VALUES (34, 31, '2023-01-31 21:39:04', '数据字典', 1, 0, 'dictionary', 'dw-dictionary', 0);
INSERT INTO `ums_menu` VALUES (35, 31, '2023-01-31 21:39:20', '元数据', 1, 0, 'metaData', 'dw-metaData', 0);
INSERT INTO `ums_menu` VALUES (36, 31, '2023-01-31 21:39:35', '数据展示', 1, 0, 'visualization', 'dw-visualization', 0);
INSERT INTO `ums_menu` VALUES (37, 31, '2023-01-31 22:14:44', 'UDF函数', 1, 0, 'udf', 'dw-udf', 0);
INSERT INTO `ums_menu` VALUES (38, 0, '2023-01-31 22:23:15', '团队资产模块', 0, 2, 'ass', 'ass', 0);
INSERT INTO `ums_menu` VALUES (39, 38, '2023-01-31 22:23:52', '技术栈', 1, 0, 'skill', 'ass-skill', 0);
INSERT INTO `ums_menu` VALUES (40, 38, '2023-01-31 22:24:06', '资源库', 1, 0, 'resources', 'ass-resources', 0);
INSERT INTO `ums_menu` VALUES (41, 21, '2023-01-31 22:24:23', '解决方案库', 1, 0, 'solution', 'ass-solution', 0);
INSERT INTO `ums_menu` VALUES (42, 38, '2023-01-31 22:24:38', '集群', 1, 0, 'cluster', 'ass-cluster', 0);
INSERT INTO `ums_menu` VALUES (43, 38, '2023-01-31 22:25:05', '解决方案库', 1, 0, 'solution', 'ass-solution', 0);
INSERT INTO `ums_menu` VALUES (44, 31, '2023-02-01 15:19:15', '数据编目', 1, 0, 'collection', 'dw-collection', 0);

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
-- Records of ums_resource
-- ----------------------------
INSERT INTO `ums_resource` VALUES (25, '2020-02-07 16:47:34', '后台用户管理', '/admin/**', '', 4);
INSERT INTO `ums_resource` VALUES (26, '2020-02-07 16:48:24', '后台用户角色管理', '/role/**', '', 4);
INSERT INTO `ums_resource` VALUES (27, '2020-02-07 16:48:48', '后台菜单管理', '/menu/**', '', 4);
INSERT INTO `ums_resource` VALUES (28, '2020-02-07 16:49:18', '后台资源分类管理', '/resourceCategory/**', '', 4);
INSERT INTO `ums_resource` VALUES (29, '2020-02-07 16:49:45', '后台资源管理', '/resource/**', '', 4);
INSERT INTO `ums_resource` VALUES (32, '2023-01-31 21:49:19', '数据血缘', '/dw/bloodRelationship/**', '数据血缘', 9);
INSERT INTO `ums_resource` VALUES (33, '2023-01-31 21:49:29', '数据开发', '/dw/development/**', '数据开发', 9);
INSERT INTO `ums_resource` VALUES (34, '2023-01-31 21:49:40', '数据字典', '/dw/dictionary/**', '数据字典', 9);
INSERT INTO `ums_resource` VALUES (35, '2023-01-31 21:49:52', '元数据', '/dw/metaData/**', '元数据', 9);
INSERT INTO `ums_resource` VALUES (36, '2023-01-31 21:50:04', '数据展示', '/dw/visualization/**', '数据展示', 9);

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
-- Records of ums_resource_category
-- ----------------------------
INSERT INTO `ums_resource_category` VALUES (4, '2020-02-05 10:23:04', '权限模块', 0);
INSERT INTO `ums_resource_category` VALUES (9, '2023-01-31 21:48:57', '数仓开发模块', 0);

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
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (5, '超级管理员', '拥有全部权限', 5, '2020-02-02 15:11:05', 1, 0);
INSERT INTO `ums_role` VALUES (9, '数仓开发工程师', '主要负责数仓', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (10, '数据可视化工程师', '主要负责数据大屏和数据报表及其数据分析', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (11, '大数据运维工程师', '主要负责Hadoop集群的维护', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (12, '大数据平台开发工程师', '主要负责大数据产品平台建设', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (13, '数据挖掘工程师', '主要负责数据深度挖掘等工作', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (14, '爬虫工程师', '主要负责数据的爬取', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (15, 'NLP算法工程师', '主要负责NLP自然语言处理的人工智能', 0, '2023-01-31 11:55:36', 1, 0);
INSERT INTO `ums_role` VALUES (16, '大数据架构师', '负责大数据产品的架构', 0, '2023-01-31 11:55:36', 1, 0);

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 250 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES (33, 1, 1);
INSERT INTO `ums_role_menu_relation` VALUES (34, 1, 2);
INSERT INTO `ums_role_menu_relation` VALUES (35, 1, 3);
INSERT INTO `ums_role_menu_relation` VALUES (36, 1, 4);
INSERT INTO `ums_role_menu_relation` VALUES (37, 1, 5);
INSERT INTO `ums_role_menu_relation` VALUES (38, 1, 6);
INSERT INTO `ums_role_menu_relation` VALUES (53, 2, 7);
INSERT INTO `ums_role_menu_relation` VALUES (54, 2, 8);
INSERT INTO `ums_role_menu_relation` VALUES (55, 2, 9);
INSERT INTO `ums_role_menu_relation` VALUES (56, 2, 10);
INSERT INTO `ums_role_menu_relation` VALUES (57, 2, 11);
INSERT INTO `ums_role_menu_relation` VALUES (96, 6, 21);
INSERT INTO `ums_role_menu_relation` VALUES (97, 6, 22);
INSERT INTO `ums_role_menu_relation` VALUES (98, 6, 23);
INSERT INTO `ums_role_menu_relation` VALUES (99, 6, 24);
INSERT INTO `ums_role_menu_relation` VALUES (100, 6, 25);
INSERT INTO `ums_role_menu_relation` VALUES (101, 7, 21);
INSERT INTO `ums_role_menu_relation` VALUES (102, 7, 22);
INSERT INTO `ums_role_menu_relation` VALUES (103, 7, 23);
INSERT INTO `ums_role_menu_relation` VALUES (104, 7, 24);
INSERT INTO `ums_role_menu_relation` VALUES (105, 7, 25);
INSERT INTO `ums_role_menu_relation` VALUES (106, 8, 21);
INSERT INTO `ums_role_menu_relation` VALUES (107, 8, 22);
INSERT INTO `ums_role_menu_relation` VALUES (108, 8, 23);
INSERT INTO `ums_role_menu_relation` VALUES (109, 8, 24);
INSERT INTO `ums_role_menu_relation` VALUES (110, 8, 25);
INSERT INTO `ums_role_menu_relation` VALUES (229, 5, 22);
INSERT INTO `ums_role_menu_relation` VALUES (230, 5, 21);
INSERT INTO `ums_role_menu_relation` VALUES (231, 5, 23);
INSERT INTO `ums_role_menu_relation` VALUES (232, 5, 24);
INSERT INTO `ums_role_menu_relation` VALUES (233, 5, 25);
INSERT INTO `ums_role_menu_relation` VALUES (234, 5, 31);
INSERT INTO `ums_role_menu_relation` VALUES (235, 5, 32);
INSERT INTO `ums_role_menu_relation` VALUES (236, 5, 33);
INSERT INTO `ums_role_menu_relation` VALUES (237, 5, 34);
INSERT INTO `ums_role_menu_relation` VALUES (238, 5, 35);
INSERT INTO `ums_role_menu_relation` VALUES (239, 5, 36);
INSERT INTO `ums_role_menu_relation` VALUES (240, 5, 37);
INSERT INTO `ums_role_menu_relation` VALUES (241, 5, 44);
INSERT INTO `ums_role_menu_relation` VALUES (242, 5, 38);
INSERT INTO `ums_role_menu_relation` VALUES (243, 5, 39);
INSERT INTO `ums_role_menu_relation` VALUES (244, 5, 40);
INSERT INTO `ums_role_menu_relation` VALUES (245, 5, 42);
INSERT INTO `ums_role_menu_relation` VALUES (246, 5, 43);
INSERT INTO `ums_role_menu_relation` VALUES (247, 9, 32);
INSERT INTO `ums_role_menu_relation` VALUES (248, 9, 31);
INSERT INTO `ums_role_menu_relation` VALUES (249, 9, 33);

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
-- Records of ums_role_resource_relation
-- ----------------------------
INSERT INTO `ums_role_resource_relation` VALUES (103, 2, 8);
INSERT INTO `ums_role_resource_relation` VALUES (104, 2, 9);
INSERT INTO `ums_role_resource_relation` VALUES (105, 2, 10);
INSERT INTO `ums_role_resource_relation` VALUES (106, 2, 11);
INSERT INTO `ums_role_resource_relation` VALUES (107, 2, 12);
INSERT INTO `ums_role_resource_relation` VALUES (142, 5, 1);
INSERT INTO `ums_role_resource_relation` VALUES (143, 5, 2);
INSERT INTO `ums_role_resource_relation` VALUES (144, 5, 3);
INSERT INTO `ums_role_resource_relation` VALUES (145, 5, 4);
INSERT INTO `ums_role_resource_relation` VALUES (146, 5, 5);
INSERT INTO `ums_role_resource_relation` VALUES (147, 5, 6);
INSERT INTO `ums_role_resource_relation` VALUES (148, 5, 8);
INSERT INTO `ums_role_resource_relation` VALUES (149, 5, 9);
INSERT INTO `ums_role_resource_relation` VALUES (150, 5, 10);
INSERT INTO `ums_role_resource_relation` VALUES (151, 5, 11);
INSERT INTO `ums_role_resource_relation` VALUES (152, 5, 12);
INSERT INTO `ums_role_resource_relation` VALUES (153, 5, 13);
INSERT INTO `ums_role_resource_relation` VALUES (154, 5, 14);
INSERT INTO `ums_role_resource_relation` VALUES (155, 5, 15);
INSERT INTO `ums_role_resource_relation` VALUES (156, 5, 16);
INSERT INTO `ums_role_resource_relation` VALUES (157, 5, 17);
INSERT INTO `ums_role_resource_relation` VALUES (158, 5, 18);
INSERT INTO `ums_role_resource_relation` VALUES (159, 5, 19);
INSERT INTO `ums_role_resource_relation` VALUES (160, 5, 20);
INSERT INTO `ums_role_resource_relation` VALUES (161, 5, 21);
INSERT INTO `ums_role_resource_relation` VALUES (162, 5, 22);
INSERT INTO `ums_role_resource_relation` VALUES (163, 5, 23);
INSERT INTO `ums_role_resource_relation` VALUES (164, 5, 24);
INSERT INTO `ums_role_resource_relation` VALUES (165, 5, 25);
INSERT INTO `ums_role_resource_relation` VALUES (166, 5, 26);
INSERT INTO `ums_role_resource_relation` VALUES (167, 5, 27);
INSERT INTO `ums_role_resource_relation` VALUES (168, 5, 28);
INSERT INTO `ums_role_resource_relation` VALUES (169, 5, 29);
INSERT INTO `ums_role_resource_relation` VALUES (170, 1, 1);
INSERT INTO `ums_role_resource_relation` VALUES (171, 1, 2);
INSERT INTO `ums_role_resource_relation` VALUES (172, 1, 3);
INSERT INTO `ums_role_resource_relation` VALUES (173, 1, 4);
INSERT INTO `ums_role_resource_relation` VALUES (174, 1, 5);
INSERT INTO `ums_role_resource_relation` VALUES (175, 1, 6);
INSERT INTO `ums_role_resource_relation` VALUES (176, 1, 23);
INSERT INTO `ums_role_resource_relation` VALUES (177, 1, 24);
INSERT INTO `ums_role_resource_relation` VALUES (178, 6, 25);
INSERT INTO `ums_role_resource_relation` VALUES (179, 6, 26);
INSERT INTO `ums_role_resource_relation` VALUES (180, 6, 27);
INSERT INTO `ums_role_resource_relation` VALUES (181, 6, 28);
INSERT INTO `ums_role_resource_relation` VALUES (182, 6, 29);
INSERT INTO `ums_role_resource_relation` VALUES (205, 7, 25);
INSERT INTO `ums_role_resource_relation` VALUES (206, 7, 26);
INSERT INTO `ums_role_resource_relation` VALUES (207, 7, 27);
INSERT INTO `ums_role_resource_relation` VALUES (208, 7, 28);
INSERT INTO `ums_role_resource_relation` VALUES (209, 7, 29);
INSERT INTO `ums_role_resource_relation` VALUES (210, 7, 31);
INSERT INTO `ums_role_resource_relation` VALUES (211, 8, 25);
INSERT INTO `ums_role_resource_relation` VALUES (212, 8, 26);
INSERT INTO `ums_role_resource_relation` VALUES (213, 8, 27);
INSERT INTO `ums_role_resource_relation` VALUES (214, 8, 28);
INSERT INTO `ums_role_resource_relation` VALUES (215, 8, 29);

SET FOREIGN_KEY_CHECKS = 1;
