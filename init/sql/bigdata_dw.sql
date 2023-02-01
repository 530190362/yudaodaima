# CREATE DATABASE if not exists `bigdata_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
#
# use `bigdata_management`;
use `mall`;

-- 项目名
DROP TABLE IF EXISTS `met_project_info`;
CREATE TABLE IF NOT EXISTS `met_project_info` (
  `met_project_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `met_project_name_en` varchar(255) NOT NULL COMMENT '项目名称 英文',
  `met_project_name_zh` varchar(255) NOT NULL COMMENT '项目名称 中文',
  `met_project_desc` varchar(255) DEFAULT NULL COMMENT '项目描述',
  `city_code` varchar(255) NOT NULL COMMENT '服务城市ID 关联表 sys_city  city_code',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`met_project_id`) USING BTREE,
  UNIQUE KEY `uk_met_project_name_en` (`met_project_name_en`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '项目表';

INSERT INTO met_project_info (met_project_id,met_project_name_en, met_project_name_zh, city_code)
VALUES
  (1,'e', 'E家富', '331003'),
  (2,'youth', '青燕归巢', '331003'),
  (3,'gfdn', '共富大脑', '331003');

-- 项目画像(标签)表
DROP TABLE IF EXISTS `dw_portrait_label_info`;
CREATE TABLE IF NOT EXISTS `dw_portrait_label_info` (
  `portrait_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `met_project_id` int(10) unsigned NOT NULL COMMENT '项目id 关联表 met_project_info  met_project_id',
  `table_en` varchar(255) NOT NULL COMMENT '表名名称 英文',
  `table_zh` varchar(255) NOT NULL COMMENT '表名名称 中文',
  `bak1` varchar(255) DEFAULT NULL COMMENT '备用1',
  `bak2` varchar(255) DEFAULT NULL COMMENT '备用2',
  `bak3` varchar(255) DEFAULT NULL COMMENT '备用3',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`portrait_id`) USING BTREE,
  UNIQUE KEY `uk_met_project_table_en` (`met_project_id`, `table_en`,`table_zh`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓项目画像表';


INSERT INTO dw_portrait_label_info (portrait_id,met_project_id, table_en, table_zh)
VALUES
  (1,1, 'dws_e_pop_user_portrait', '自然人画像表'),
  (2,1, 'dws_e_pop_family_portrait', '家庭画像');


-- 主题字典表
DROP TABLE IF EXISTS `dw_theme_dict`;
CREATE TABLE IF NOT EXISTS `dw_theme_dict` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `theme_en` varchar(255) NOT NULL COMMENT '主题英文',
  `theme_zh` varchar(255) NOT NULL COMMENT '主题中文',
  `theme_desc` varchar(255) DEFAULT NULL COMMENT '主题描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_theme_en` (`theme_en`) USING BTREE,
  UNIQUE KEY `uk_theme_zh` (`theme_zh`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓画像主题字典码表';

INSERT INTO dw_theme_dict (id,theme_en, theme_zh)
VALUES
  (1,'base', '基本信息'),
  (2,'asset', '资产情况'),
  (3,'edu', '教育'),
  (4,'work', '工作'),
  (5,'help', '帮扶'),
  (6,'special', '特殊'),
  (7,'health', '医疗卫健'),
  (8,'forecast', '算法预测'),
  (9,'ins', '社保');



-- 维度字典表
DROP TABLE IF EXISTS `dw_dim_dict`;
CREATE TABLE IF NOT EXISTS `dw_dim_dict` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dim_en` varchar(255) NOT NULL COMMENT '维度英文',
  `dim_zh` varchar(255) NOT NULL COMMENT '维度中文',
  `dim_desc` varchar(255) DEFAULT NULL COMMENT '维度描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_dim_en` (`dim_en`) USING BTREE,
  UNIQUE KEY `uk_dim_zh` (`dim_zh`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓画像维度字典码表';


INSERT INTO dw_dim_dict (id,dim_en, dim_zh)
VALUES
  (1,'001', '自然人属性'),
  (2,'002', '常居属性'),
  (3,'003', '地理属性'),
  (4,'004', '基本指标'),
  (5,'005', '婚姻信息'),
  (6,'006', '资产情况'),
  (7,'007', '教育情况'),
  (8,'008', '工作基本保障'),
  (9,'009', '职业信息'),
  (10,'010', '社保信息'),
  (11,'011', '职业状况'),
  (12,'012', '失业状况'),
  (13,'013', '特殊保障对象'),
  (14,'014', '保障救助类'),
  (15,'015', '救助记录类'),
  (16,'016', '补贴记录类'),
  (17,'017', '特殊人群'),
  (18,'018', '健康状况'),
  (19,'019', '医疗支出'),
  (20,'020', '致贫分析'),
  (21,'021', '收入'),
  (22,'022', '支出'),
  (23,'023', '指数');


INSERT INTO dw_dim_dict (id,dim_en, dim_zh)
VALUES
  (24,'024', '家庭形态'),
  (25,'025', '人口结构'),
  (26,'026', '家庭经营'),
  (27,'027', '家庭就业状况'),
  (28,'028', '家庭属性');





DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE IF NOT EXISTS `sys_city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增非业务主键',
  `city_code` varchar(255) NOT NULL COMMENT '城市编码',
  `city_name_en` varchar(255) DEFAULT NULL COMMENT '城市名称 英文',
  `city_name_zh` varchar(255) DEFAULT NULL COMMENT '城市名称 中文',
  `longitude` decimal(10, 6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10, 6) DEFAULT NULL COMMENT '纬度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_city_code` (`city_code`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '系统城市表';


INSERT INTO sys_city (id,city_code, city_name_en,city_name_zh,longitude,latitude)
VALUES
  (1,'331003', 'TaiZhou','台州市',121.43,28.68),
  (2,'330100', 'HangZhou','杭州市',120.15,30.28);


-- 标签获取方式字典表
DROP TABLE IF EXISTS `dw_label_get_dict`;
CREATE TABLE IF NOT EXISTS `dw_label_get_dict` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label_get_zh` varchar(255) NOT NULL COMMENT '标签获取方式中文',
  `label_get_desc` varchar(255) DEFAULT NULL COMMENT '标签获取方式描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_label_get_zh` (`label_get_zh`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓画像标签来源字典码表';

INSERT INTO dw_label_get_dict (id, label_get_zh, label_get_desc)
VALUES
  (1,'描述类', '常见来源,可以在表的字段直接取,或者if,casewhen,decode转换取值'),
  (2,'统计类', '常见来源,也称计算类,通常需要聚合count,sum,max,min,avg等函数 '),
  (3,'规则类', '侧重业务人员给的偏经验指标,偏向个人主观'),
  (4,'时序记录类', "字段类型为JSON,里面key的值是子指标,[对象1,对象2,对象3],collect_set(TO_JSON(MAP('key1','vaule1','key2','vaule2')))"),
  (5,'算法类', 'AHP层次分析法  +  机器学习预测法 + 神经网络预测法');


-- 标签数值类型字典表
DROP TABLE IF EXISTS `dw_label_num_dict`;
CREATE TABLE IF NOT EXISTS `dw_label_num_dict` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label_num_zh` varchar(255) NOT NULL COMMENT '标签数值类型中文',
  `label_num_desc` varchar(255) DEFAULT NULL COMMENT '标签数值类型描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_label_num_zh` (`label_num_zh`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓标签数值类型字典表';

INSERT INTO dw_label_num_dict (id,label_num_zh, label_num_desc)
VALUES
  (1,'二分类', '结果 1是  0否'),
  (2,'多分类', '可枚举 ,数值有限个'),
  (3,'无穷类', '数值无穷无尽，但把年龄放在此范围');


-- 画像明细表
DROP TABLE IF EXISTS `dw_portrait_label_detail`;
CREATE TABLE IF NOT EXISTS `dw_portrait_label_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `portrait_id` int(10) unsigned NOT NULL COMMENT '项目画像(标签)表ID  关联表 dw_portrait_label_info  portrait_id',
  `theme_id` int(10) unsigned NOT NULL COMMENT '主题id 关联表 dw_theme_dict  id',
  `dim_id` int(10) unsigned NOT NULL COMMENT '维度id 关联表 dw_dim_dict  id',
  `label_name` varchar(255) NOT NULL COMMENT '标签名称',
  `label_comment` varchar(255) NOT NULL COMMENT '标签描述',
  `label_get_id` int(10) unsigned NOT NULL COMMENT '标签获取类型 关联表 dw_label_get_dict  id',
  `label_num_id`int(10) unsigned NOT NULL COMMENT '标签数值类型 关联表 dw_label_num_dict  id',
  `label_desc` varchar(255) DEFAULT NULL COMMENT '标签描述',
  `label_range` varchar(255) DEFAULT NULL COMMENT '标签数值范围 针对多分类的情况',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓画像标签明细表';


-- 数仓开发UDF函数库
DROP TABLE IF EXISTS `dw_funciton_main`;
CREATE TABLE IF NOT EXISTS `dw_funciton_main` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `funciton_name` varchar(255) NOT NULL COMMENT '函数名称',
  `funciton_type` varchar(255) DEFAULT NULL COMMENT '函数类型 UDF/UDAF/UDTF',
  `funciton_class` varchar(255) NOT NULL COMMENT '函数所属JAVA类名',
  `funciton_desc` varchar(255) DEFAULT NULL COMMENT '函数描述',
  `funciton_parameter` varchar(255) DEFAULT NULL COMMENT '函数参数说明',
  `funciton_return` varchar(255) DEFAULT NULL COMMENT '函数返回值说明',
  `funciton_format` varchar(255) DEFAULT NULL COMMENT '函数命令格式',
  `funciton_example` varchar(255) DEFAULT NULL COMMENT '函数示例',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_funciton_name` (`funciton_name`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '数仓开发UDF函数库';


-- 解决方案落地经验库
DROP TABLE IF EXISTS `met_project_solution_exp`;
CREATE TABLE IF NOT EXISTS `met_project_solution_exp` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `solution_name` varchar(255) NOT NULL COMMENT '解决方案名称',
  `solution_desc` varchar(255) DEFAULT NULL COMMENT '解决方案描述',
  `solution_dingding_url` varchar(255) DEFAULT NULL COMMENT '解决方案钉钉URL',
  `solution_gitlab_url` varchar(255) DEFAULT NULL COMMENT '解决方案GitLabURL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '解决方案落地经验库';

INSERT INTO `met_project_solution_exp` (id,solution_name, solution_desc,solution_dingding_url,solution_gitlab_url)
VALUES
  (1,'元数据mysql数据导出','基于shell+java自定义多数据源,数据导出成excel',NULL,'http://extgitlab.ytbig.cn:8011/homjay666/Export-Meta.git'),
  (2,'mysql数据备份','基于shell+python+mysql数据每日自定义导出',NULL,'http://extgitlab.ytbig.cn:8011/homjay666/Backup-Mysql.git'),
  (3,'mysql数据同步','基于shell+dataX每日数据同步',NULL,'http://extgitlab.ytbig.cn:8011/bigdata-group/Prosperity-Echo-Data-Task.git');




-- 互联网资源文件
DROP TABLE IF EXISTS `met_internet_resource`;
CREATE TABLE IF NOT EXISTS `met_internet_resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) NOT NULL COMMENT '解决方案名称',
  `resource_desc` varchar(255) DEFAULT NULL COMMENT '解决方案描述',
  `resource_url` varchar(255) DEFAULT NULL COMMENT '解决方案GitLabURL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '互联网资源库';

INSERT INTO `met_internet_resource` (id,resource_name, resource_desc,resource_url)
VALUES
  (1,'python官网', NULL,'https://www.python.org/'),
  (2,'python pypi', 'python pip install 文件','https://pypi.org/'),
  (3,'java 官网', NULL,'https://www.java.com/zh-CN/'),
  (4,'maven 依赖', 'maven 的 pom.xml 的参数','https://mvnrepository.com/'),
  (5,'scala 官网', NULL,'https://scala-lang.org/'),
  (6,'hadoop 官网', NULL,'https://hadoop.apache.org/'),
  (7,'spark 官网', NULL,'https://spark.apache.org/');


-- 项目开发标准库
DROP TABLE IF EXISTS `met_project_dev_standard`;
CREATE TABLE IF NOT EXISTS `met_project_dev_standard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `standard_name` varchar(255) NOT NULL COMMENT '开发标准规范名称',
  `standard_desc` varchar(255) DEFAULT NULL COMMENT '开发标准规范描述',
  `standard_url` varchar(255) DEFAULT NULL COMMENT '开发标准规范URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '项目开发标准库';

INSERT INTO `met_project_dev_standard` (id,standard_name, standard_desc,standard_url)
VALUES
  (1,'shell 开发规范', 'Shell脚本开发规范','https://zh-google-styleguide.readthedocs.io/en/latest/'),
  (2,'python 开发规范', NULL,'https://weread.qq.com/web/bookDetail/42a32930813ab6ddeg018ad1'),
  (3,'java 开发规范', NULL,'https://weread.qq.com/web/bookDetail/f5a3215072096af9f5ac0ec'),
  (4,'sql 开发规范', NULL, NULL),
  (5,'hql 开发规范', NULL, NULL),
  (6,'数仓 开发规范', NULL, NULL);



--  画像综合指标视图
CREATE OR REPLACE VIEW `view_portrait_total_analyse` AS
SELECT 
portrait_id
,'标签数' as type
,count(*) as cnt 
from `dw_portrait_label_detail`
GROUP BY portrait_id
union all
SELECT portrait_id ,'主题数' as type,count(*) cnt from (
SELECT 
portrait_id
,theme_id
,count(*) as cnt 
from `dw_portrait_label_detail`
GROUP BY portrait_id,theme_id) t
GROUP BY portrait_id
union all
SELECT portrait_id ,'维度数' as type,count(*) cnt from (
SELECT 
portrait_id
,dim_id
,count(*) as cnt 
from `dw_portrait_label_detail`
GROUP BY portrait_id,dim_id) t
GROUP BY portrait_id
ORDER BY portrait_id;


-- 主题分析视图  
CREATE OR REPLACE VIEW `view_portrait_theme_analyse` AS
SELECT 
t1.portrait_id
,theme_zh
,cnt
,concat( round( cnt / total_cnt * 100, 2 ), '%' ) AS cnt_per
 from (
select portrait_id
,theme_id
,count(*) as cnt 
from dw_portrait_label_detail
GROUP BY portrait_id,theme_id) t1
left JOIN
(select portrait_id, count(*) as total_cnt from dw_portrait_label_detail GROUP BY portrait_id) t2
on t1.portrait_id=t2.portrait_id
left join dw_theme_dict t3
on t1.theme_id =t3.id;

-- 维度分析视图  
CREATE OR REPLACE VIEW `view_portrait_dim_analyse` AS
SELECT 
t1.portrait_id
,dim_zh
,cnt
,concat( round( cnt / total_cnt * 100, 2 ), '%' ) AS cnt_per
 from (
select portrait_id
,dim_id
,count(*) as cnt 
from dw_portrait_label_detail
GROUP BY portrait_id,dim_id) t1
left JOIN
(select portrait_id, count(*) as total_cnt from dw_portrait_label_detail GROUP BY portrait_id) t2
on t1.portrait_id=t2.portrait_id
left join dw_dim_dict t3
on t1.dim_id =t3.id;


-- 标签获取类型指标视图
CREATE OR REPLACE VIEW `view_portrait_label_get_analyse` AS
SELECT 
t1.portrait_id
,label_get_zh
,cnt
,concat( round( cnt / total_cnt * 100, 2 ), '%' ) AS cnt_per
 from (
select portrait_id
,label_get_id
,count(*) as cnt 
from dw_portrait_label_detail
GROUP BY portrait_id,label_get_id) t1
left JOIN
(select portrait_id, count(*) as total_cnt from dw_portrait_label_detail GROUP BY portrait_id) t2
on t1.portrait_id=t2.portrait_id
left join dw_label_get_dict t3
on t1.label_get_id =t3.id;


-- 标签数值类型指标视图  
CREATE OR REPLACE VIEW `view_portrait_label_num_analyse` AS
SELECT 
t1.portrait_id
,label_num_zh
,cnt
,concat( round( cnt / total_cnt * 100, 2 ), '%' ) AS cnt_per
 from (
select portrait_id
,label_num_id
,count(*) as cnt 
from dw_portrait_label_detail
GROUP BY portrait_id,label_num_id) t1
left JOIN
(select portrait_id, count(*) as total_cnt from dw_portrait_label_detail GROUP BY portrait_id) t2
on t1.portrait_id=t2.portrait_id
left join dw_label_num_dict t3
on t1.label_num_id =t3.id;



-- 获取建字段及其创建的视图
CREATE OR REPLACE VIEW `view_portrait_make_sql` AS
SELECT
portrait_id
,theme_zh
,dim_zh
,label_name
,label_name_full
,label_comment
,label_desc
,label_get_zh
,label_num_zh
,concat(', ',label_name,' AS ',label_name_full,' -- ',label_comment)  as do_sql
from (
select 
portrait_id
,theme_zh
,dim_zh
,label_name
,concat(theme_en,'_',dim_en,'_',label_name) as label_name_full
,label_comment
,label_desc
,label_get_zh
,label_num_zh
from dw_portrait_label_detail t1
LEFT JOIN dw_theme_dict t2
on t1.theme_id = t2.id
LEFT JOIN dw_dim_dict t3
on t1.dim_id = t3.id
LEFT JOIN dw_label_get_dict t4
on t1.label_get_id = t4.id
LEFT JOIN dw_label_num_dict t5
on t1.label_num_id = t5.id) t;