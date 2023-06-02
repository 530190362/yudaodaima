package com.bigdata.backstage.modules.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;

import java.util.List;

/**
 * <p>
 * 数据资产-ODPS表 服务类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
public interface MetDataTableService extends IService<MetDataTable> {

    //同步表级别
    void syncTable();

    //查看表是否存在
    boolean selectTable(String table);

    //数据集成-3个指标
    DataSourceTotalDto selectOdsTable(Integer dwId);

    //数据集成-折线图
    List<DataSourceHistoryDto> selectOdsHistory(Integer limit,Integer dwId);

    //数据集成-表单(分页模糊查询)
    IPage<MetDataTable> selectOdsPage(DataSourcePageDto dto);

    ////获取表信息
    MetDataTable getTableInfo(DataSourceInfoDto dto);
}
