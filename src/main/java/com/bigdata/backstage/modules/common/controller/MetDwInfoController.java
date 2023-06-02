package com.bigdata.backstage.modules.common.controller;

import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据项目-项目管理
 */
@RestController
@RequestMapping("/common/metDwInfo")
@Api(tags = "数据项目-项目管理")
@Tag(name = "数据项目-项目管理", description = "MetDwInfoController")
public class MetDwInfoController {

    private final MetDwInfoService metDwInfoService;


    @Autowired
    public MetDwInfoController(MetDwInfoService metDwInfoService) {
        this.metDwInfoService = metDwInfoService;
    }

    /**
     * 获取当前用户[数仓名称(英文)]的数仓ID
     */
    @GetMapping("getUserId/{dwNameEn}")
    @ApiOperation(value = "获取当前用户[数仓名称(英文)]的数仓ID")
    public CommonResult<MetDwInfo> getUserId(@PathVariable String dwNameEn) {
        MetDwInfo dwInfo = metDwInfoService.getDwInfo(dwNameEn);
        return CommonResult.success(dwInfo);
    }

    /**
     * 获取数仓ID映射表名
     */
    @GetMapping("getDwMapTable/{dwId}")
    @ApiOperation(value = "获取数仓ID映射表名")
    public CommonResult<MetDwTableMapInfo> getDwMapTable(@PathVariable int dwId) {
        MetDwTableMapInfo dwMapInfo = metDwInfoService.getDwMapInfo(dwId);
        return CommonResult.success(dwMapInfo);
    }

}
