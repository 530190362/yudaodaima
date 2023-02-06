package com.bigdata.backstage.modules.homepage.service;


import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
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

    /**
     * 数据资产（记录数）变化
     * @param days
     * @return
     */
    List<DataChangeVo> queryDataChangeCount(Integer days);

    /**
     * 数据资产（占用空间）变化
     * @param days
     * @return
     */
    List<DataChangeVo> queryDataChangeSize(Integer days);

    /**
     * 数据榜单-占用空间
     * @return
     */
    List<DataSizeTop> getDataSizeTop();

    /**
     * 数据榜单-总记录数
     * @return
     */
    List<DataCountTop> getDataCountTop();
}
