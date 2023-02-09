package com.bigdata.backstage.modules.source.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "数据集成")
@Tag(name = "数据集成", description = "MetDataSourceController")
@RestController
@RequestMapping("/source")
public class MetDataSourceController {


    @Autowired
    private MetDataTableService metDataTableService;

    @ApiOperation(value = "数据集成-3个指标")
    @GetMapping("/total")
    public CommonResult syncColumn() {
        DataSourceTotalDto dataSourceTotalDto = metDataTableService.selectOdsTable();
        return CommonResult.success(dataSourceTotalDto);
    }


    @ApiOperation(value = "数据集成-折线图")
    @GetMapping("/history/{limit}")
    public CommonResult syncColumn(@PathVariable Integer limit) {
        List<DataSourceHistoryDto> resutList = metDataTableService.selectOdsHistory(limit);
        return CommonResult.success(resutList);
    }

    @ApiOperation(value = "数据集成-表单(分页模糊查询)")
    @PostMapping("/list")
    public CommonResult list(@PathVariable DataSourcePageDto dto) {
        IPage<MetDataTable> pageist= metDataTableService.selectOdsPage(dto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }



}
