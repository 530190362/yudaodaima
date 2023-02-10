package com.bigdata.backstage.modules.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;

import java.util.List;

/**
 * <p>
 * 数据资产-ODPS表字段 服务类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
public interface MetDataColumnService extends IService<MetDataColumn> {

    //同步字段级别
    void syncColumn();

    ////获取表信息
    List<MetDataColumn> getColumnInfo(DataSourcePageDto dto);
}
