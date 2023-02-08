package com.bigdata.backstage.modules.common.service.impl;

import com.bigdata.backstage.modules.common.model.MetDataSyncLog;
import com.bigdata.backstage.modules.common.mapper.MetDataSyncLogMapper;
import com.bigdata.backstage.modules.common.service.MetDataSyncLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据资产-数据同步日志表 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Service
public class MetDataSyncLogServiceImpl extends ServiceImpl<MetDataSyncLogMapper, MetDataSyncLog> implements MetDataSyncLogService {

}
