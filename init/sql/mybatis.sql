insert into met_data_table(dw_id, tbl_name, tbl_comment, tbl_create_time, tbl_update_time, is_pt, pt_last,
                           tbl_lifecycle, tbl_type, tbl_size, tbl_col_num, all_row_count, row_count)
select 1 as dw_id,
       tbl_name,
       tbl_comment,
       tbl_create_time,
       tbl_update_time,
       is_pt,
       pt_last,
       tbl_lifecycle,
       tbl_type,
       tbl_size,
       tbl_col_num,
       all_row_count,
       row_count
from view_met_data_table t2
where not exists(select 1 from met_data_table t1 where t1.tbl_name = t2.tbl_name);

update met_data_table t1
    inner join view_met_data_table t2 on t1.tbl_name = t2.tbl_name
set t1.tbl_name =t2.tbl_name,
    t1.tbl_comment =t2.tbl_comment,
    t1.tbl_create_time =t2.tbl_create_time,
    t1.tbl_update_time =t2.tbl_update_time,
    t1.is_pt =t2.is_pt,
    t1.pt_last =t2.pt_last,
    t1.tbl_lifecycle =t2.tbl_lifecycle,
    t1.tbl_type =t2.tbl_type,
    t1.tbl_size =t2.tbl_size,
    t1.tbl_col_num =t2.tbl_col_num,
    t1.all_row_count =t2.all_row_count,
    t1.row_count =t2.row_count;

UPDATE met_data_table t1 SET is_deleted = 1
WHERE t1.tbl_name not in (select t2.tbl_name from view_met_data_table t2);


-- #######################################################################
-- #######################################################################

insert into met_data_column(dw_id, tbl_name, col_name, col_type, col_comment)
select 1 as dw_id, tbl_name, col_name, col_type, col_comment
from view_met_data_column
where not exists(select 1
                 from met_data_column
                 where tbl_name = view_met_data_column.tbl_name
                   and col_name = view_met_data_column.col_name);

update met_data_column t1
    inner join view_met_data_column t2 on concat(t1.tbl_name, t1.col_name) = concat(t2.tbl_name, t2.col_name)
set t1.col_type    = t2.col_type,
    t1.col_comment = t2.col_comment;


UPDATE met_data_column t1 SET is_deleted = 1
WHERE concat(t1.tbl_name, t1.col_name) not in (select concat(t2.tbl_name, t2.col_name) from view_met_data_column t2);




