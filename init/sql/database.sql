drop database if exists `bigdata-backstage`;
CREATE DATABASE `bigdata-backstage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4general_ci;

drop database if exists `gfdn_odps_export`;
CREATE DATABASE `gfdn_odps_export` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 写入
use `gfdn_odps_export`;
source /data/mysql/dim_gfdn_meta_blood_relation_detail.sql;
source /data/mysql/dim_gfdn_meta_detail_outline.sql;
source /data/mysql/dim_gfdn_meta_quality.sql;

use `gfdn_odps_export`;
source /data/mysql/dim_qygc_meta_blood_relation_detail.sql;
source /data/mysql/dim_qygc_meta_detail_outline.sql;
source /data/mysql/dim_qygc_meta_quality.sql;

# 写入
use `bigdata-backstage`;
source /data/mysql/bigdata-backstage.sql;






