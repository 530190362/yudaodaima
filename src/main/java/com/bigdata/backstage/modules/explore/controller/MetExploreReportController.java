package com.bigdata.backstage.modules.explore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreReportTableDto;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataColumn;
import com.bigdata.backstage.modules.explore.model.ViewExploreDataTable;
import com.bigdata.backstage.modules.explore.service.MetExploreReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 数据勘探-数据勘探报告表 前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
@Api(tags = "数据探查-勘探报告")
@Tag(name = "数据探查-勘探报告", description = "MetExploreReportController")
@RestController
@RequestMapping("/explore/report")
public class MetExploreReportController {


    @Autowired
    private MetExploreReportService metExploreReportService;

    @ApiOperation(value = "勘探报告模糊查询")
    @PostMapping("/list")
    public CommonResult<CommonPage<ViewExploreDataTable>> list(
            @Valid @RequestBody MetExploreReportDto metExploreReportDto) {
        IPage<ViewExploreDataTable> pageist = metExploreReportService.selectPage(metExploreReportDto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    @ApiOperation(value = "勘探报告表/字段明确信息")
    @PostMapping("/info")
    public CommonResult exploreTablInfo(
            @RequestBody MetExploreReportTableDto dto) {
        ViewExploreDataTable tableInfo = metExploreReportService.exploreTablInfo(dto);
        List<ViewExploreDataColumn> columnsInfo = metExploreReportService.exploreColumnsInfo(dto);
        HashMap<String, Object> result = new HashMap<>();
        result.put("tableInfo", tableInfo);
        result.put("columnsInfo", columnsInfo);
        return CommonResult.success(result);
    }


}

