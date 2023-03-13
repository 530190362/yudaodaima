package com.bigdata.backstage.modules.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 数仓主表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
@RestController
@Api(tags = "数据项目-项目管理")
@Tag(name = "数据项目-项目管理", description = "MetDwInfoController")
@RequestMapping("/common/metDwInfo")
public class MetDwInfoController {

    @Autowired
    private MetDwInfoService metDwInfoService;

    @ApiOperation(value = "获取当前用户[数仓名称(英文)]的数仓ID")
    @GetMapping("getUserId/{dwNameEn}")
    public CommonResult getUserId(@PathVariable String dwNameEn) {
        QueryWrapper<MetDwInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("dw_name_en", dwNameEn);
        return CommonResult.success(metDwInfoService.getOne(wrapper));
    }

}

