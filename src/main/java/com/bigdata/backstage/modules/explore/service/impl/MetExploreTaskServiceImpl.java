package com.bigdata.backstage.modules.explore.service.impl;

import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.explore.model.MetExploreTask;
import com.bigdata.backstage.modules.explore.mapper.MetExploreTaskMapper;
import com.bigdata.backstage.modules.explore.service.MetExploreTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据勘探-数据勘探操作表 服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Service
public class MetExploreTaskServiceImpl extends ServiceImpl<MetExploreTaskMapper, MetExploreTask> implements MetExploreTaskService {

    @Autowired
    private MetDataTableService metDataTableService;

    //添加数据勘探
    @Override
    public void add(MetExploreTask metExploreTask) {
        boolean isExist = metDataTableService.selectTable(metExploreTask.getTblName());
        if (isExist) {
            baseMapper.insert(metExploreTask);
        } else {
            Asserts.fail("表不存在");
        }
    }
}
