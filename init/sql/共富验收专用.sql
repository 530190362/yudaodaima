update met_norm_dict
set create_user = 'gfdn',
    update_user = 'gfdn';



update met_norm_node
set create_user = 'gfdn',
    update_user = 'gfdn';

update met_norm_table
set create_user = 'gfdn',
    update_user = 'gfdn';

UPDATE met_data_table
SET is_deleted=0;

