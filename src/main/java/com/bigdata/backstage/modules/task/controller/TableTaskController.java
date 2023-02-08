package com.bigdata.backstage.modules.task.controller;

import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.task.service.TableTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "ScheduledTaskController")
@Tag(name = "ScheduledTaskController", description = "同步管理")
@RestController
@RequestMapping("/task")
public class TableTaskController {

    @Autowired
    private TableTaskService tableTaskService;

    @ApiOperation(value = "同步表数据资产概览表met_data_overview")
    @GetMapping("metDataOverview")
    public CommonResult syncTabelOverview() {
        tableTaskService.syncTabelOverview();
        return CommonResult.success("同步表数据资产概览表met_data_overview");
    }

    @ApiOperation(value = "数据资产标签表met_data_label")
    @GetMapping("metDataLabel")
    public CommonResult syncTableLabel() {
        tableTaskService.syncTableLabel();
        return CommonResult.success("数据资产标签表met_data_label");
    }

}
