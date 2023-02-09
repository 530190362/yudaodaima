package com.bigdata.backstage.modules.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.backstage.modules.common.model.MetDataTable;

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
}
