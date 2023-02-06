package com.bigdata.backstage.modules.met.controller;


import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.common.exception.Asserts;
import com.bigdata.backstage.modules.met.model.MetNormDict;
import com.bigdata.backstage.modules.met.service.MetNormDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 数据标准-字典库 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */

@Api(tags = "MetNormDictController")
@Tag(name = "MetNormDictController", description = "数据标准-字典库")
@RestController
@RequestMapping("/met/metNormDict")
public class MetNormDictController {

    @Autowired
    private MetNormDictService metNormDictService;


    //导入数据字典接口
    @ApiOperation(value = "数据字典导入")
    @PostMapping("/importDictData")
    public CommonResult importDictData(@RequestParam("file") MultipartFile file) {
        try {
            metNormDictService.importDictData(file);
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("写入失败，数据格式错误");
        }
    }


    //导出数据字典接口
    @ApiOperation(value = "数据字典导出")
    @GetMapping
    public CommonResult exportDict(HttpServletResponse response) {
        metNormDictService.exportDictData(response);
        return CommonResult.success("ok");
    }

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public CommonResult findChildData(@PathVariable Long id) {
        List<MetNormDict> list = metNormDictService.findChildData(id);
        return CommonResult.success(list);
    }


}

