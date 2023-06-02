package com.bigdata.backstage.modules.homepage.service;


import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataOverviewVo;
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
    DataOverviewVo getDataOverview(Integer dwId);

    /**
     * 数据资产（记录数）变化
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeCount(Integer days, Integer dwId);

    /**
     * 数据资产（占用空间）变化
     * @param days
     * @return
     */
    List<DataChangeVo> getDataChangeSize(Integer days, Integer dwId);

    /**
     * 数据榜单-占用空间
     * @return
     */
    List<DataSizeTop> getDataSizeTop(Integer dwId);

    /**
     * 数据榜单-总记录数
     * @return
     */
    List<DataCountTop> getDataCountTop(Integer dwId);

    /**
     * 数据榜单-最近新增
     * @return
     */
    List<DataCountTop> getDataNewTop(Integer dwId);

    /**
     * 数据榜单-新增记录数
     * @return
     */
    List<DataCountTop> getDataNewRowTop(Integer dwId);
}
