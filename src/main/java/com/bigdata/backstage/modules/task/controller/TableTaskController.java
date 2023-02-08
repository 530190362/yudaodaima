package com.bigdata.backstage.modules.task.controller;

import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.task.service.TableTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "同步管理")
@Tag(name = "同步管理", description = "TableTaskController")
@RestController
@RequestMapping("/task")
public class TableTaskController {

    @Autowired
    private TableTaskService tableTaskService;

    @ApiOperation(value = "同步表级别")
    @GetMapping("table")
    public CommonResult syncTabel() {
        tableTaskService.syncTabel();
        return CommonResult.success("提交成功,同步表级别");

    }

    @ApiOperation(value = "同步字段级别")
    @GetMapping("column")
    public CommonResult syncColumn() {
        tableTaskService.syncColumn();
        return CommonResult.success("提交成功,同步字段级别");
    }

}
