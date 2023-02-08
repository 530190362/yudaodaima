package com.bigdata.backstage.modules.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 数据资产-ODPS表 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
public interface MetDataTableMapper extends BaseMapper<MetDataTable> {


    void syncTableInsert(@Param("dwID") Integer dwId);

    void syncTableUpdate();

    void syncTableDelete();
}
