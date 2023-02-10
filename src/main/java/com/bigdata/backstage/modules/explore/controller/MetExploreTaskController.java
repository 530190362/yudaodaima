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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据勘探-数据勘探操作表 前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */

@Api(tags = "数据探查-探查操作")
@Tag(name = "数据探查-探查操作", description = "MetExploreTaskController")
@RestController
@RequestMapping("/explore")
public class MetExploreTaskController {

    @Autowired
    private MetExploreTaskService metExploreTaskService;


    @ApiOperation(value = "添加数据勘探")
    @PostMapping("/add")
    public CommonResult add(@RequestBody MetExploreTaskDto metExploreTaskDto) {
        MetExploreTask metExploreTask = BeanUtil.copyProperties(metExploreTaskDto, MetExploreTask.class);
        metExploreTask.setCreateDate(DateUtil.date());
        metExploreTaskService.add(metExploreTask);
        return CommonResult.success("添加成功");
    }

}

