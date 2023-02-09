package com.bigdata.backstage.modules.explore.service;

import com.bigdata.backstage.modules.explore.model.MetExploreTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据勘探-数据勘探操作表 服务类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
public interface MetExploreTaskService extends IService<MetExploreTask> {

    //添加数据勘探
    void add(MetExploreTask metExploreTask);
}
