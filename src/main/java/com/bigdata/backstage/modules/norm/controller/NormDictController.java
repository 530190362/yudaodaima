package com.bigdata.backstage.modules.norm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.norm.dto.NormDictDto;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 数据标准-字典库 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */

@Api(tags = "MetNormDictController")
@Tag(name = "MetNormDictController", description = "数据标准-字典库")
@RestController
@RequestMapping("/norm/dict")
public class NormDictController {

    @Autowired
    private NormDictService normDictService;

    //导入数据字典接口
    @ApiOperation(value = "数据字典导入")
    @PostMapping("/importDictData")
    @CacheEvict(value = "bigdata:dict", allEntries = true)
    public CommonResult importDictData(@RequestParam("file") MultipartFile file) {
        try {
            normDictService.importDictData(file);
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("写入失败，数据格式错误");
        }
    }

    //导出数据字典接口
    @ApiOperation(value = "数据字典导出")
    @GetMapping
    public CommonResult exportDict(HttpServletResponse response) {
        normDictService.exportDictData(response);
        return CommonResult.success("ok");
    }

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public CommonResult findChildData(@PathVariable Long id) {
        List<NormDict> list = normDictService.findChildData(id);
        return CommonResult.success(list);
    }


    //分页模糊查询
    @ApiOperation(value = "分页模糊查询")
    @PostMapping("/list")
    public CommonResult<CommonPage<NormDict>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody NormDictDto normDictDto) {
        IPage<NormDict> pageist= normDictService.selectPage(normDictDto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(pageist));
    }

    //根据id修改数据字典
    @ApiOperation(value = "修改数据字典的值")
    @PostMapping("/update/{id}")
    @CacheEvict(value = "bigdata:dict", allEntries = true)
    public CommonResult update(@PathVariable Long id, @RequestBody NormDictDto normDictDto) {
        NormDict normDict = BeanUtil.copyProperties(normDictDto, NormDict.class);
        normDict.setId(id);
        boolean upadate = normDictService.updateById(normDict);
        if (upadate) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    //根据id删除数据字典
    @ApiOperation(value = "根据id删除数据字典")
    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "bigdata:dict", allEntries = true)
    public CommonResult delete(@PathVariable Long id) {
        boolean remove = normDictService.removeById(id);
        if (remove) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    //根据id新增数据字典
    @ApiOperation(value = "新增数据字典")
    @PostMapping("/add")
    @CacheEvict(value = "bigdata:dict", allEntries = true)
    public CommonResult add(@RequestBody NormDictDto normDictDto) {
        NormDict normDict = BeanUtil.copyProperties(normDictDto, NormDict.class);
        boolean save = normDictService.save(normDict);
        if (save) {
            return CommonResult.success("新增成功");
        } else {
            return CommonResult.failed("新增失败");
        }
    }


}



