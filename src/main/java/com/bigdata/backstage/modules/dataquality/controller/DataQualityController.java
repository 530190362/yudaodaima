package com.bigdata.backstage.modules.dataquality.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleTaskRelationMapper;
import com.bigdata.backstage.modules.common.model.MetQualityRule;
import com.bigdata.backstage.modules.common.model.MetQualityRuleTaskRelation;
import com.bigdata.backstage.modules.common.service.MetQualityRuleService;
import com.bigdata.backstage.modules.dataquality.dto.RulePageDto;
import com.bigdata.backstage.modules.dataquality.service.DataQualityService;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityRulePageVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据质量
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/quality")
public class DataQualityController {

    @Autowired
    private DataQualityService dataQualityService;
    @Autowired
    private MetQualityRuleService metQualityRuleService;
    @Autowired
    private MetQualityRuleTaskRelationMapper metQualityRuleTaskRelationMapper;

    @ApiOperation(value = "质检规则分页查询")
    @PostMapping("/pagelist")
    public CommonResult<IPage<DataQualityRulePageVo>> pageAssetList(@RequestBody RulePageDto rulePageDto) {
        IPage<DataQualityRulePageVo> dataQualityRulePageVoIPage = dataQualityService.pageQueryList(rulePageDto);
        return CommonResult.success(dataQualityRulePageVoIPage);
    }

    @ApiOperation(value = "规则类型列表")
    @GetMapping(value = "/getRuleList")
    public CommonResult<List<Map<String,String>>> getRuleList() {
        List<Map<String, String>> ruleList = dataQualityService.getRuleList();
        return CommonResult.success(ruleList);
    }

    @ApiOperation(value = "新增修改")
    @PostMapping(value = "/addOrUpdate")
    public CommonResult addOrUpdate(@RequestBody MetQualityRule metQualityRule) {
        if (metQualityRule.getId() == null){
            boolean save = metQualityRuleService.save(metQualityRule);
            if (save) {
                return CommonResult.success("新增成功");
            } else {
                return CommonResult.failed("新增失败");
            }
        }else {
            boolean update = metQualityRuleService.updateById(metQualityRule);
            if (update) {
                return CommonResult.success("修改成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        Long tblNum = metQualityRuleTaskRelationMapper.selectCount(new QueryWrapper<MetQualityRuleTaskRelation>()
                .eq("rule_id", id).eq("is_delete", 0));
        if (tblNum > 0) {
            return CommonResult.failed("删除失败,标签下有关联表");
        }
        boolean delete = metQualityRuleService.removeById(id);
        if (delete) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
}
