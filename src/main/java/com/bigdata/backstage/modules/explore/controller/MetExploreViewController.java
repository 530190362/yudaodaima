package com.bigdata.backstage.modules.explore.controller;

import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.explore.dto.MetExploreViewHistoryDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreViewIndexDto;
import com.bigdata.backstage.modules.explore.service.MetExploreTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "数据探查-探查概览")
@Tag(name = "数据探查-探查概览", description = "MetExploreViewController")
@RestController
@RequestMapping("/explore/view")
public class MetExploreViewController {

    @Autowired
    private MetExploreTaskService metExploreTaskService;

    @ApiOperation(value = "探查概览(指标)")
    @GetMapping("/total")
    public CommonResult total() {
        MetExploreViewIndexDto metExploreViewIndexDto = metExploreTaskService.getViewTotal();
        return CommonResult.success(metExploreViewIndexDto);
    }

    @ApiOperation(value = "探查概览(折线图)")
    @GetMapping("/history/{id}")
    public CommonResult history(@PathVariable Integer id) {
        List<MetExploreViewHistoryDto> resultList = metExploreTaskService.getViewHistory(id);
        return CommonResult.success(resultList);
    }


}
