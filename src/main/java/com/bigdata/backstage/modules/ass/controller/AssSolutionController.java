package com.bigdata.backstage.modules.ass.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.bigdata.backstage.modules.ass.service.AssSolutionService;
import com.bigdata.backstage.modules.ass.vo.AssSolutionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ##################################
 * Description: 解决方案落地经验库 前端控制器
 * author: zhj
 * date: 2023/1/26 14:52
 * ##################################
 */

@Api(tags = "AssSolutionController")
@Tag(name = "AssSolutionController", description = "解决方案落地经验库")
@RestController
@RequestMapping("/ass/solution")
public class AssSolutionController {

    @Autowired
    private AssSolutionService assSolutionService;


    @ApiOperation("新增指定解决方案")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody AssSolutionVo assSolutionVo) {
        assSolutionService.create(assSolutionVo);
        return CommonResult.success(null);
    }

    @ApiOperation("删除指定解决方案")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Integer id) {
        assSolutionService.delete(id);
        return CommonResult.success(null);

    }

    @ApiOperation("修改指定解决方案")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Integer id,
                               @RequestBody AssSolutionVo assSolutionVo) {
        assSolutionService.update(id, assSolutionVo);
        return CommonResult.success(null);
    }


    @ApiOperation("分页查询解决方案")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<AssSolution>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody AssSolutionVo assSolutionVo) {
        IPage<AssSolution> pageist = assSolutionService.selectPage(assSolutionVo, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

}


