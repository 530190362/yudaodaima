package com.bigdata.backstage.modules.norm.controller;


import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/norm/dict")
public class NormDictController {

    @Autowired
    private NormDictService normDictService;


    //导入数据字典接口
    @ApiOperation(value = "数据字典导入")
    @PostMapping("/importDictData")
    public CommonResult importDictData(@RequestParam("file") MultipartFile file) {
        try {
            normDictService.importDictData(file);
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
        normDictService.exportDictData(response);
        return CommonResult.success("ok");
    }

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public CommonResult findChildData(@PathVariable Long id) {
        List<NormDict> list = normDictService.findChildData(id);
        return CommonResult.success(list);
    }


}

