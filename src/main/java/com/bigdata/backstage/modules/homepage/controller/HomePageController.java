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
 * ODPS元数据记录拉链表 前端控制器
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
    @RequestMapping(value = "/dataoverview", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String,Object>> getDataOverview() {
        Map<String, Object> data = homePageService.getDataOverview();
        return CommonResult.success(data);
    }

    @ApiOperation(value = "数据资产变化")
    @RequestMapping(value = "/datachange", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DataChangeVo>> getDataChange(@RequestParam(value = "days", required = false, defaultValue = "0") Integer days,
                                                            @RequestParam(value = "type", required = false, defaultValue = "0") Integer type) {
        List<DataChangeVo> dataChangeVo = new ArrayList<>();
        if (DataChangeTypeEnum.TOTAL_COUNT.getKey().equals(type)){
            dataChangeVo = homePageService.queryDataChangeCount(days);
        }else {
            dataChangeVo = homePageService.queryDataChangeSize(days);
        }
        return CommonResult.success(dataChangeVo);
    }

    @ApiOperation(value = "数据占用空间top10")
    @RequestMapping(value = "/dataSizeTop", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DataSizeTop>> getDataSizeTop() {
        List<DataSizeTop> dataSizeTop = homePageService.getDataSizeTop();
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据总记录数top10")
    @RequestMapping(value = "/dataCountTop", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataCountTop() {
        List<DataCountTop> dataSizeTop = homePageService.getDataCountTop();
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据最近新增")
    @RequestMapping(value = "/dataNewTop", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataNewTop() {
        List<DataCountTop> dataSizeTop = homePageService.getDataNewTop();
        return CommonResult.success(dataSizeTop);
    }

    @ApiOperation(value = "数据新增记录数")
    @RequestMapping(value = "/dataNewRowTop", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DataCountTop>> getDataNewRowTop() {
        List<DataCountTop> dataSizeTop = homePageService.getDataNewRowTop();
        return CommonResult.success(dataSizeTop);
    }

}


