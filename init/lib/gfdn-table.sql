 CREATE TABLE `dim_gfdn_meta_quality` (
  `table_name` varchar(255) DEFAULT NULL,
  `table_comment` varchar(255) DEFAULT NULL,
  `col_name` varchar(255) DEFAULT NULL,
  `col_comment` varchar(255) DEFAULT NULL,
  `distinct_count` varchar(255) DEFAULT NULL,
  `total_count` varchar(255) DEFAULT NULL,
  `max_len` varchar(255) DEFAULT NULL,
  `min_len` varchar(255) DEFAULT NULL,
  `max_value` varchar(255) DEFAULT NULL,
  `col_id` int(11) DEFAULT NULL,
  `min_value` varchar(255) DEFAULT NULL,
  `spend_name` int(11) DEFAULT NULL,
  `null_count` varchar(255) DEFAULT NULL,
  `null_rate` varchar(255) DEFAULT NULL,
  `spend_time` varchar(255) DEFAULT NULL,
  `etl_tm` varchar(255) DEFAULT NULL,
  `dt` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE `dim_gfdn_meta_detail_outline` (
  `is_size` int(11) DEFAULT NULL,
  `pro_name` varchar(255) DEFAULT NULL,
  `tbl_level` varchar(255) DEFAULT NULL,
  `tbl_name` varchar(255) DEFAULT NULL,
  `tbl_comment` varchar(255) DEFAULT NULL,
  `col_name` varchar(255) DEFAULT NULL,
  `col_type` varchar(255) DEFAULT NULL,
  `col_comment` varchar(255) DEFAULT NULL,
  `is_time` int(11) DEFAULT NULL,
  `tbl_col_num` varchar(255) DEFAULT NULL,
  `pt_num` int(11) DEFAULT NULL,
  `col_size` int(11) DEFAULT NULL,
  `index1_name` varchar(255) DEFAULT NULL,
  `index2_name` varchar(255) DEFAULT NULL,
  `index3_name` varchar(255) DEFAULT NULL,
  `is_pt` varchar(255) DEFAULT NULL,
  `pt_last` varchar(255) DEFAULT NULL,
  `tbl_create_time` varchar(255) DEFAULT NULL,
  `tbl_update_time` varchar(255) DEFAULT NULL,
  `tbl_lifecycle` varchar(255) DEFAULT NULL,
  `tbl_type` varchar(255) DEFAULT NULL,
  `tbl_size` varchar(255) DEFAULT NULL,
  `etl_tm` varchar(255) DEFAULT NULL,
  `dt` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;



CREATE TABLE `dim_gfdn_meta_blood_relation_detail` (
  `ins_name` varchar(255) DEFAULT NULL,
  `ins_id` int(11) DEFAULT NULL,
  `group_tm` int(11) DEFAULT NULL,
  `tbl_out_en` varchar(255) DEFAULT NULL,
  `tbl_out_zh` varchar(255) DEFAULT NULL,
  `group_en` int(11) DEFAULT NULL,
  `tbl_in_en` varchar(255) DEFAULT NULL,
  `tbl_in_zh` varchar(255) DEFAULT NULL,
  `etl_tm` varchar(255) DEFAULT NULL,
  `dt` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

