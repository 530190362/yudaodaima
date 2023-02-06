package com.bigdata.backstage.modules.homepage.service;


import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ODPS元数据记录拉链表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface HomePageService {

    /**
     * 首页概览-数据统计
     * @return
     */
    Map<String, Object> getDataOverview();

    List<DataChangeVo> queryDataChangeCount(Integer days);

    List<DataChangeVo> queryDataChangeSize(Integer days);

    List<DataSizeTop> getDataSizeTop();
}
