package com.bigdata.backstage.modules.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.backstage.modules.common.mapper.MetDataColumnMapper;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.common.service.MetDataColumnService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据资产-ODPS表字段 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Service
public class MetDataColumnServiceImpl extends ServiceImpl<MetDataColumnMapper, MetDataColumn> implements MetDataColumnService {

}
