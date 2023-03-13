package com.bigdata.backstage.modules.homepage.controller;

import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.homepage.enums.DataChangeTypeEnum;
import com.bigdata.backstage.modules.homepage.vo.DataChangeVo;
import com.bigdata.backstage.modules.homepage.service.HomePageService;
import com.bigdata.backstage.modules.homepage.vo.DataCountTop;
import com.bigdata.backstage.modules.homepage.vo.DataSizeTop;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/homepage")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @ApiOperation(value = "数据资产概览")
    @GetMapping("dataoverview/{dwId}")
    @ResponseBody
    public CommonResult<Map<String, Object>> getDataOverview(@PathVariable Long dwId) {
        System.out.println(dwId);
        Map<String, Object> data = homePageService.getDataOverview(dwId);
        return CommonResult.success(data);
    }

    @ApiOperation(value = "数据资产变化")
    @GetMapping("datachange")
    @ResponseBody
    public CommonResult<List<DataChangeVo>> getDataChange(@RequestParam(value = "days", required = false, defaultValue = "0") Integer days,
                                                          @RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
                                                          @RequestParam(value = "dwId", required = false, defaultValue = "0") Integer dwId
    ) {
        List<DataChangeVo> dataChangeVo = new ArrayList<>();
        if (DataChangeTypeEnum.TOTAL_COUNT.getKey().equals(type)) {
            dataChangeVo = homePageService.queryDataChangeCount(days,dwId);
        } else {
            dataChangeVo = homePageService.queryDataChangeSize(days,dwId);
        }
        return CommonResult.success(dataChangeVo);
    }

    @ApiOperation(value = "数据占用空间top10")
    @GetMapping(value = "/dataSizeTop/{dwId}")
    @ResponseBody
    public CommonResult<List<DataSizeTop>> getDataSizeTop(@PathVariable Integer dwId) {
        List<DataSizeTop> dataSizeTop = homePageService.getDataSizeTop(dwId);
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据总记录数top10")
    @GetMapping(value = "/dataCountTop/{dwId}")
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataCountTop(@PathVariable Integer dwId) {
        List<DataCountTop> dataSizeTop = homePageService.getDataCountTop(dwId);
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据最近新增")
    @GetMapping(value = "/dataNewTop/{dwId}")
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataNewTop(@PathVariable Integer dwId) {
        List<DataCountTop> dataSizeTop = homePageService.getDataNewTop(dwId);
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据新增记录数")
    @GetMapping(value = "/dataNewRowTop/{dwId}")
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataNewRowTop(@PathVariable Integer dwId) {
        List<DataCountTop> dataSizeTop = homePageService.getDataNewRowTop(dwId);
        return CommonResult.success(dataSizeTop);
    }

}


