package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据资产-ODPS表 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
public interface MetDataTableMapper extends MPJBaseMapper<MetDataTable> {


    //数据写入
    void syncTableInsert(@Param("dwId") Integer dwId,@Param("tableName") String tableName);

    //数据更新
    void syncTableUpdate(@Param("dwId") Integer dwId,@Param("tableName") String tableName);

    //数据删除(逻辑删除)
    void syncTableDelete(@Param("dwId") Integer dwId,@Param("tableName") String tableName);

    //数据恢复(逻辑恢复)
    void syncTableRecover(@Param("dwId") Integer dwId,@Param("tableName") String tableName);


    DataSourceTotalDto selectOdsIndex(@Param("tableName") String tableName);


    List<DataSourceHistoryDto> selectOdsHistory(@Param("limit") Integer limit,
                                                @Param("tableName") String tableName);


}
