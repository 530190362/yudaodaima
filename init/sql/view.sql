create or replace view view_explore_data_column
as
SELECT
    `met_explore_report`.`dw_id` AS `dw_id`,
    `met_explore_report`.`table_name` AS `table_name`,
    `met_explore_report`.`col_name` AS `col_name`,
    `met_explore_report`.`col_type` AS `col_type`,
    `met_explore_report`.`col_comment` AS `col_comment`,
    `met_explore_report`.`max_len` AS `max_len`,
    `met_explore_report`.`min_len` AS `min_len`,
    `met_explore_report`.`max_value` AS `max_value`,
    `met_explore_report`.`min_value` AS `min_value`,
    `met_explore_report`.`null_count` AS `null_count`,
    `met_explore_report`.`null_rate` AS `null_rate`,
    `met_explore_report`.`value_kind_json` AS `value_kind_json`,
    `met_explore_report`.`is_only_value` AS `is_only_value`
FROM
    `met_explore_report`;

create or replace view view_explore_data_table
as
SELECT
    `met_explore_report`.`dw_id` AS `dw_id`,
    `met_explore_report`.`table_name` AS `table_name`,
    `met_explore_report`.`table_comment` AS `table_comment`,
    sum( `met_explore_report`.`distinct_count` ) AS `distinct_count`,
    sum( `met_explore_report`.`total_count` ) AS `total_count`,
    max( `met_explore_report`.`spend_time` ) AS `spend_time`,
    min( `met_explore_report`.`etl_tm` ) AS `etl_tm`
FROM
    `met_explore_report`
GROUP BY
    `met_explore_report`.`dw_id`,
    `met_explore_report`.`table_name`,
    `met_explore_report`.`table_comment`



delete from met_explore_report where dw_id=3;

delete from met_quality_rule where dw_id=3;

