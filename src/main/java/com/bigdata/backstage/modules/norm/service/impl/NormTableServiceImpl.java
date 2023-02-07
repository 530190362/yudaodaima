package com.bigdata.backstage.modules.norm.service.impl;

import com.bigdata.backstage.modules.norm.model.NormTable;
import com.bigdata.backstage.modules.norm.mapper.NormTableMapper;
import com.bigdata.backstage.modules.norm.service.NormTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据标准-表命名规范 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Service
public class NormTableServiceImpl extends ServiceImpl<NormTableMapper, NormTable> implements NormTableService {

}
