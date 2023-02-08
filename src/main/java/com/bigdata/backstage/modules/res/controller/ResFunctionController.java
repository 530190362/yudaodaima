package com.bigdata.backstage.modules.res.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.norm.dto.NormTableDto;
import com.bigdata.backstage.modules.norm.model.NormTable;
import com.bigdata.backstage.modules.res.dto.ResFunctionDto;
import com.bigdata.backstage.modules.res.model.ResFunction;
import com.bigdata.backstage.modules.res.service.ResFunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 资源管理-UDF函数管理 前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2023-02-07
 */

@Api(tags = "ResFunctionController")
@Tag(name = "ResFunctionController", description = "资源管理-UDF函数库")
@RestController
@RequestMapping("/res/resFunction")
public class ResFunctionController {

    @Autowired
    private ResFunctionService resFunctionService;

    //导出EXCEL数据
    @ApiOperation(value = "导出EXCEL数据(全量)")
    @GetMapping
    public void exportFunctionTable(HttpServletResponse response) {
        resFunctionService.exportFunctionData(response);
    }

    //分页模糊查询
    @ApiOperation(value = "分页模糊查询")
    @PostMapping("list")
    public CommonResult<CommonPage<ResFunction>> list(@Valid @RequestBody ResFunctionDto resFunctionDto) {
        IPage<ResFunction> pageist = resFunctionService.selectPage(resFunctionDto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    //新增
    @ApiOperation(value = "新增")
    @PostMapping("add")
    public CommonResult add(@RequestBody ResFunctionDto resFunctionDto) {
        resFunctionService.add(resFunctionDto);
        return CommonResult.success("写入成功");
    }

    //删除
    @ApiOperation(value = "删除")
    @DeleteMapping("delete/{id}")
    public CommonResult delete(@PathVariable Integer id) {
        resFunctionService.removeById(id);
        return CommonResult.success("删除成功");
    }


    //TODO 改
    @ApiOperation(value = "修改")
    @PostMapping("update")
    public CommonResult update(@RequestBody ResFunctionDto resFunctionDto) {
        ResFunction resFunction = BeanUtil.copyProperties(resFunctionDto, ResFunction.class);
        resFunctionService.updateById(resFunction);
        return CommonResult.success("修改成功");
    }


}

