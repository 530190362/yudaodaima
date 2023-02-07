package com.bigdata.backstage.modules.norm.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.norm.dto.NormNodeDto;
import com.bigdata.backstage.modules.norm.dto.NormRootDto;
import com.bigdata.backstage.modules.norm.model.NormNode;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.service.NormNodeService;
import com.bigdata.backstage.modules.norm.service.NormRootService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 数据标准-任务节点 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "MetNormNodeController")
@Tag(name = "MetNormNodeController", description = "数据标准-节点")
@RestController
@RequestMapping("/norm/node")
public class NormNodeController {

    @Autowired
    private NormNodeService normNodeService;


    //分页模糊查询
    @ApiOperation(value = "分页模糊查询")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<NormNode>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody NormNodeDto normNodeDto) {
        IPage<NormNode> pageist = normNodeService.selectPage(normNodeDto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    //新增
    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody NormNodeDto normNodeDto) {
        NormNode normNode = BeanUtil.copyProperties(normNodeDto, NormNode.class);
        boolean save = normNodeService.save(normNode);
        if (save) {
            return CommonResult.success("新增成功");
        } else {
            return CommonResult.failed("新增失败");
        }
    }

    //修改
    @ApiOperation(value = "修改")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody NormNodeDto normNodeDto) {
        NormNode normNode = BeanUtil.copyProperties(normNodeDto, NormNode.class);
        normNode.setId(id);
        boolean update = normNodeService.updateById(normNode);
        if (update) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    //删除
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        boolean delete = normNodeService.removeById(id);
        if (delete) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    //导出为EXCEL(全量)
    @ApiOperation(value = "导出为EXCEL(全量)")
    @GetMapping
    public void exporNode(HttpServletResponse response) {
        normNodeService.exportNodeData(response);
    }
}

