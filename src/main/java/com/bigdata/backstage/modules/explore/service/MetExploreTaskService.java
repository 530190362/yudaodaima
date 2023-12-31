package com.bigdata.backstage.modules.explore.service;

import com.bigdata.backstage.modules.explore.dto.MetExploreViewHistoryDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreViewIndexDto;
import com.bigdata.backstage.modules.explore.model.MetExploreTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    //探查概览(指标)
    MetExploreViewIndexDto getViewTotal(Integer dwId);

    //探查概览(折线图)
    List<MetExploreViewHistoryDto> getViewHistory(Integer limit,Integer dwId);
}
