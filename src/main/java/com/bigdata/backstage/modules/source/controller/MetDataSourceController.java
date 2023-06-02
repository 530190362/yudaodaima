package com.bigdata.backstage.modules.source.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDataColumn;
import com.bigdata.backstage.modules.common.model.MetDataTable;
import com.bigdata.backstage.modules.common.service.MetDataColumnService;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import com.bigdata.backstage.modules.source.dto.DataSourceHistoryDto;
import com.bigdata.backstage.modules.source.dto.DataSourceInfoDto;
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

    @Autowired
    private NormDictService normDictService;


    // 获取ODS的数据源头(数据集成)
    @ApiOperation("数据集成-系统源头")
    @GetMapping("/type")
    public CommonResult odsType() {
        List<NormDict> normDictList = normDictService.getOdsType();
        return CommonResult.success(normDictList);
    }


    @ApiOperation(value = "数据集成-3个指标")
    @GetMapping("/total/{dwId}")
    public CommonResult odsTotalIndex(@PathVariable Integer dwId) {
        DataSourceTotalDto dataSourceTotalDto = metDataTableService.selectOdsTable(dwId);
        return CommonResult.success(dataSourceTotalDto);
    }


    @ApiOperation(value = "数据集成-折线图")
    @GetMapping(value = "/history")
    public CommonResult odsHistoryIndex(Integer limit, Integer dwId) {
        List<DataSourceHistoryDto> resutList = metDataTableService.selectOdsHistory(limit, dwId);
        return CommonResult.success(resutList);
    }

    @ApiOperation(value = "数据集成-表单(分页模糊查询)")
    @PostMapping("/list")
    public CommonResult list(@RequestBody DataSourcePageDto dto) {
        IPage<MetDataTable> pageist = metDataTableService.selectOdsPage(dto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    @ApiOperation(value = "数据集成-(表/字段)展示")
    @PostMapping("/info")
    public CommonResult tableInfo(@RequestBody DataSourceInfoDto dto) {
        //获取表信息
        MetDataTable table = metDataTableService.getTableInfo(dto);
        //获取列信息
        List<MetDataColumn> column = metDataColumnService.getColumnInfo(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("taleInfo", table);
        result.put("columnInfo", column);
        return CommonResult.success(result);
    }


}
