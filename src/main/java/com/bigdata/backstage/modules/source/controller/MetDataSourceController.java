package com.bigdata.backstage.modules.source.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.model.ViewMetDataTable;
import com.bigdata.backstage.modules.common.model.ViewMetDetailOutline;
import com.bigdata.backstage.modules.common.service.MetDataColumnService;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourcePageDto;
import com.bigdata.backstage.modules.source.dto.DataSourceTotalDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "数据集成")
@Tag(name = "数据集成", description = "MetDataSourceController")
@RestController
@RequestMapping("/source")
public class MetDataSourceController {


    @Autowired
    private MetDataTableService metDataTableService;

    @Autowired
    private MetDataColumnService metDataColumnService;

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
    public CommonResult list(@RequestBody DataSourcePageDto dto) {
        IPage<ViewMetDataTable> pageist = metDataTableService.selectOdsPage(dto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    @ApiOperation(value = "数据集成-(表/字段)展示")
    @PostMapping("/info}")
    public CommonResult info(@RequestBody DataSourcePageDto dto) {
        //获取表信息
        MetDataTable table = metDataTableService.getTableInfo(dto.getTableName());
        //获取列信息
        List<MetDataColumn> column = metDataColumnService.getColumnInfo(dto.getTableName());
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("table", table);
        resultMap.put("column", column);

        return CommonResult.success(resultMap);
    }


}
