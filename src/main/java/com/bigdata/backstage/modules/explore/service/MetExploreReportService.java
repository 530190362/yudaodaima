package com.bigdata.backstage.modules.explore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportTableDto;
import com.bigdata.backstage.modules.explore.model.MetExploreReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataColumn;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataTable;

import java.util.List;

/**
 * <p>
 * 数据勘探-数据勘探报告表 服务类
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
public interface MetExploreReportService extends IService<MetExploreReport> {

    //勘探报告模糊查询
    IPage<ViewExploreDataTable> selectPage(MetExploreReportDto metExploreReportDto);

    //勘探报告表明确信息
    ViewExploreDataTable exploreTablInfo(MetExploreReportTableDto dto);

    //勘探报告字段明确信息
    List<ViewExploreDataColumn> exploreColumnsInfo(MetExploreReportTableDto dto);
}
