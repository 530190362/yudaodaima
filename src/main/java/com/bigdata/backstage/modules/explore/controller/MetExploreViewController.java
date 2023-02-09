package com.bigdata.backstage.modules.explore.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.explore.dto.MetExploreTaskDto;
import com.bigdata.backstage.modules.explore.model.MetExploreTask;
import com.bigdata.backstage.modules.explore.service.MetExploreTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "数据探查-探查概览")
@Tag(name = "数据探查-探查概览", description = "MetExploreViewController")
@RestController
@RequestMapping("/explore/view")
public class MetExploreViewController {

    @Autowired
    private MetExploreTaskService metExploreTaskService;




    @ApiOperation(value = "添加数据勘探")
    @GetMapping("/total")
    public CommonResult total() {


        return CommonResult.success("添加成功");
    }



}
