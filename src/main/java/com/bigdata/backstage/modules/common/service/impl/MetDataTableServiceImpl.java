package com.bigdata.backstage.modules.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataTableMapper;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据资产-ODPS表 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Service
public class MetDataTableServiceImpl extends ServiceImpl<MetDataTableMapper, MetDataTable> implements MetDataTableService {

    @Value("${dw.id}")
    private Integer dwId;

    @Autowired
    private  MetDataTableMapper metDataTableMapper;
    //同步表级别
    @Override
    public void syncTable() {
        metDataTableMapper.syncTableInsert(dwId);
        metDataTableMapper.syncTableUpdate();
        metDataTableMapper.syncTableDelete();
    }
}
