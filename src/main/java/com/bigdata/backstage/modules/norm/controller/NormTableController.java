package com.bigdata.backstage.modules.norm.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.norm.dto.NormRootDto;
import com.bigdata.backstage.modules.norm.dto.NormTableDto;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.model.NormTable;
import com.bigdata.backstage.modules.norm.service.NormTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据标准-表命名规范 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "数据标准-表规范")
@Tag(name = "数据标准-表规范", description = "NormTableController")
@RestController
@RequestMapping("/norm/table")
public class NormTableController {

    @Autowired
    private NormTableService normTableService;


    @ApiOperation(value = "返回数仓分层名称")
    @GetMapping(value = "/dwLevel")
    public CommonResult dwLevel() {
        List<NormTable> detailedList = normTableService.list();
        List<String> result = new ArrayList<>();
        for (NormTable normTable : detailedList) {
            result.add(normTable.getLevel());
        }
        return CommonResult.success(result);
    }

    //导出EXCEL数据
    @ApiOperation(value = "导出EXCEL数据(全量)")
    @GetMapping
    public void exportTable(HttpServletResponse response) {
        normTableService.exportTableData(response);
    }


    //分页模糊查询
    @ApiOperation(value = "分页模糊查询")
    @PostMapping("list")
    public CommonResult<CommonPage<NormTable>> list(@Valid @RequestBody NormTableDto normTableDto) {
        IPage<NormTable> pageist = normTableService.selectPage(normTableDto);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    //新增
    @ApiOperation(value = "新增")
    @PostMapping("add")
    public CommonResult add(@RequestBody NormTableDto normTableDto) {
        normTableService.add(normTableDto);
        return CommonResult.success("写入成功");
    }

    //删除
    @ApiOperation(value = "删除")
    @DeleteMapping("delete/{id}")
    public CommonResult delete(@PathVariable Integer id) {
        normTableService.removeById(id);
        return CommonResult.success("删除成功");
    }

    //修改
    @ApiOperation(value = "修改")
    @PostMapping("update")
    public CommonResult update(@RequestBody NormTableDto normTableDto) {
        NormTable normTable = BeanUtil.copyProperties(normTableDto, NormTable.class);
        normTableService.updateById(normTable);
        return CommonResult.success("修改成功");
    }
}

