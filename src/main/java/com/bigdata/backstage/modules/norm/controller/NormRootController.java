package com.bigdata.backstage.modules.norm.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.bigdata.backstage.modules.ass.vo.AssSolutionVo;
import com.bigdata.backstage.modules.norm.dto.NormRootDto;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.service.NormRootService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据标准-字根库 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Api(tags = "MetNormRootController")
@Tag(name = "MetNormRootController", description = "数据标准-词根库")
@RestController
@RequestMapping("/norm/root")
public class NormRootController {

    @Autowired
    private NormRootService normRootService;

    //分页查询
    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<NormRoot>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody NormRootDto normRootDto) {
        IPage<NormRoot> pageist = normRootService.selectPage(normRootDto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    //新增
    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody NormRootDto normRootDto) {
        normRootService.add(normRootDto);
        return CommonResult.success("写入成功");
    }


    //删除
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        boolean isOk = normRootService.removeById(id);
        if (!isOk) {
            return CommonResult.failed("删除失败");
        }
        return CommonResult.success("删除成功");
    }

    //更新
    @ApiOperation(value = "更新")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody NormRootDto normRootDto) {
        NormRoot normRoot = BeanUtil.copyProperties(normRootDto, NormRoot.class);
        normRoot.setId(id);
        boolean isOk = normRootService.updateById(normRoot);
        if (!isOk) {
            return CommonResult.failed("更新失败");
        }
        return CommonResult.success("更新成功");
    }
}

