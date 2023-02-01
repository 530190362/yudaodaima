package com.bigdata.backstage.modules.dw.controller;


import cn.hutool.json.JSONArray;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.dw.service.DwOdpsService;
import com.bigdata.backstage.modules.dw.vo.OdpsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ##################################
 * Description: odps相关接口
 * author: zhj
 * date: 2023/2/1 14:52
 * ##################################
 */

@Api(tags = "DwOdpsController")
@Tag(name = "DwOdpsController", description = "ODPS里JSON格式转换")
@RestController
@RequestMapping("/dw/odps")
public class DwOdpsController {

    @Autowired
    private DwOdpsService dwOdpsService;

    @ApiOperation("JSON格式转换")
    @PostMapping(value = "/json")
    public CommonResult dwOdpsJson(@RequestBody OdpsVo odpsVo) {
        JSONArray sourceData =  odpsVo.getJsonAr();
        String newData = dwOdpsService.jsonConvert(sourceData);
        return CommonResult.success(newData);
    }
}
