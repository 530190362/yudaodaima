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
    void syncTableInsert(@Param("dwId") Integer dwId);


    //数据更新
    void syncTableUpdate(@Param("dwId") Integer dwId);


    //数据删除(逻辑删除)
    void syncTableDelete(@Param("dwId") Integer dwId);

    //数据恢复(逻辑恢复)
    void syncColumnRecover(@Param("dwId") Integer dwId);

    // //数据集成-3个指标
    DataSourceTotalDto selectOdsIndexGfdn();

    DataSourceTotalDto selectOdsIndexQygc();


    List<DataSourceHistoryDto> selectOdsHistory(@Param("limit") Integer limit,
                                                @Param("dwId") Integer dwId);

    List<DataSourceHistoryDto> selectOdsHistoryGfdn(@Param("limit") Integer limit
    );

    List<DataSourceHistoryDto> selectOdsHistoryQygc(@Param("limit") Integer limit
    );
}
