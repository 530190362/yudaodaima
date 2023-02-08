package com.bigdata.backstage.modules.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 数据资产-ODPS表字段 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
public interface MetDataColumnMapper extends BaseMapper<MetDataColumn> {



    //数据写入
    void syncColumnInsert(@Param("dwID") Integer dwId);

    //数据更新
    void syncColumnUpdate();

    //数据删除(逻辑删除)
    void syncColumnDelete();

    //数据恢复(逻辑恢复)
    void syncColumnRecover();
}
