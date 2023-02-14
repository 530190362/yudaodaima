package com.bigdata.backstage.modules.dataquality.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleTaskRelationMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityTaskMapper;
import com.bigdata.backstage.modules.common.model.*;
import com.bigdata.backstage.modules.common.service.MetDataTableService;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.common.service.MetQualityRuleService;
import com.bigdata.backstage.modules.common.service.MetQualityTaskService;
import com.bigdata.backstage.modules.dataquality.dto.RulePageDto;
import com.bigdata.backstage.modules.dataquality.dto.TaskPageDto;
import com.bigdata.backstage.modules.dataquality.dto.TaskUpdateDto;
import com.bigdata.backstage.modules.dataquality.dto.WarnPageDto;
import com.bigdata.backstage.modules.dataquality.service.DataQualityService;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityRulePageVo;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityTaskDetailVo;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityTaskPageVo;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityWarnPageVo;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private MetDwInfoService metDwInfoService;
    @Autowired
    private NormDictService normDictService;
    @Autowired
    private MetQualityTaskMapper metQualityTaskMapper;
    @Autowired
    private MetQualityTaskService metQualityTaskService;
    @Autowired
    private MetDataTableService metDataTableService;

    @ApiOperation(value = "质检规则分页查询")
    @PostMapping("/pagelist")
    public CommonResult<IPage<DataQualityRulePageVo>> pageRuleList(@RequestBody RulePageDto rulePageDto) {
        IPage<DataQualityRulePageVo> dataQualityRulePageVoIPage = dataQualityService.pageQueryList(rulePageDto);
        return CommonResult.success(dataQualityRulePageVoIPage);
    }

    @ApiOperation(value = "获取规则类型列表")
    @GetMapping(value = "/getRuleList")
    public CommonResult<List<Map<String,String>>> getRuleList() {
        List<Map<String, String>> ruleList = dataQualityService.getRuleList();
        return CommonResult.success(ruleList);
    }

    @ApiOperation(value = "质检规则详情")
    @GetMapping(value = "/getRuleDetailById")
    public CommonResult<DataQualityRulePageVo> getRuleDetailById(Integer ruleId) {
        MetQualityRule ruleServiceById = metQualityRuleService.getById(ruleId);
        DataQualityRulePageVo dataQualityRulePageVo = new DataQualityRulePageVo();
        BeanUtils.copyProperties(ruleServiceById, dataQualityRulePageVo);
        MetDwInfo metDwInfo = metDwInfoService.getById(ruleServiceById.getDwId());
        dataQualityRulePageVo.setProjectName(metDwInfo.getDwNameZn());
        NormDict normDict = normDictService.getById(ruleServiceById.getRuleType());
        dataQualityRulePageVo.setRuleTypeName(normDict.getName());
        Long count = metQualityTaskMapper.selectCount(new QueryWrapper<MetQualityTask>().eq("rule_id", ruleId).eq("is_delete", 0));
//        Long count = qualityRuleTaskRelationMapper.selectCount(new QueryWrapper<MetQualityRuleTaskRelation>().eq("rule_id", record.getId()).eq("is_delete", 0));
        dataQualityRulePageVo.setRuleBindNum(count.intValue());

        return CommonResult.success(dataQualityRulePageVo);
    }

    @ApiOperation(value = "规则新增修改")
    @PostMapping(value = "/addOrUpdateRule")
    public CommonResult addOrUpdateRule(@RequestBody MetQualityRule metQualityRule) {
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

    @ApiOperation(value = "规则删除")
    @DeleteMapping(value = "/deleteRule/{id}")
    public CommonResult deleteRule(@PathVariable Long id) {
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

    @ApiOperation(value = "质检任务分页查询")
    @PostMapping("/pageTasklist")
    public CommonResult<IPage<DataQualityTaskPageVo>> getpageTasklist(@RequestBody TaskPageDto taskPageDto) {
        IPage<DataQualityTaskPageVo> qualityTaskPageVoPage = dataQualityService.getpageTasklist(taskPageDto);
        return CommonResult.success(qualityTaskPageVoPage);
    }

    @ApiOperation(value = "质检任务详情")
    @GetMapping(value = "/getTaskDetailById")
    public CommonResult<DataQualityTaskDetailVo> getTaskDetailById(Integer taskId) {
        MetQualityTask metQualityTask = metQualityTaskService.getById(taskId);
        DataQualityTaskDetailVo dataQualityTaskVo = new DataQualityTaskDetailVo();
        BeanUtils.copyProperties(metQualityTask, dataQualityTaskVo);
        MetDwInfo metDwInfo = metDwInfoService.getById(metQualityTask.getDwId());
        dataQualityTaskVo.setProjectName(metDwInfo.getDwNameZn());
        MetQualityRule qualityRule = metQualityRuleService.getById(metQualityTask.getRuleId());
        MetDataTable dataTable = metDataTableService.getById(metQualityTask.getBindTbl());
        if (qualityRule != null) {
            dataQualityTaskVo.setRuleName(qualityRule.getRuleName());
        }
        if (dataTable != null) {
            dataQualityTaskVo.setBindTblName(dataTable.getTblName());
        }
        return CommonResult.success(dataQualityTaskVo);
    }

    @ApiOperation(value = "获取绑定规则列表")
    @GetMapping(value = "/getBindRuleList")
    public CommonResult<List<Map<String,String>>> getBindRuleList() {
        List<Map<String, String>> ruleList = dataQualityService.getBindRuleList();
        return CommonResult.success(ruleList);
    }

    @ApiOperation(value = "任务新增修改")
    @PostMapping(value = "/addOrUpdateTask")
    public CommonResult addOrUpdateTask(@RequestBody MetQualityTask metQualityTask) {
        if (metQualityTask.getId() == null){
            boolean save = metQualityTaskService.save(metQualityTask);
            if (save) {
                return CommonResult.success("新增成功");
            } else {
                return CommonResult.failed("新增失败");
            }
        }else {
            boolean update = metQualityTaskService.updateById(metQualityTask);
            if (update) {
                return CommonResult.success("修改成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        }
    }

    @ApiOperation(value = "任务删除")
    @DeleteMapping(value = "/deleteTask/{id}")
    public CommonResult deleteTask(@PathVariable Long id) {
        boolean delete = metQualityTaskService.removeById(id);
        if (delete) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation(value = "任务上线下线")
    @PostMapping (value = "/updateTask")
    public CommonResult updateTask(@RequestBody TaskUpdateDto updateDto) {
        MetQualityTask qualityTask = metQualityTaskService.getById(updateDto.getId());
        qualityTask.setStatus(updateDto.getStatus());
        boolean update = metQualityTaskService.updateById(qualityTask);
        if (update) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    @ApiOperation(value = "质检预警分页查询")
    @PostMapping("/pageWarnlist")
    public CommonResult<IPage<DataQualityWarnPageVo>> getpageWarnlist(@RequestBody WarnPageDto warnPageDto) {
        IPage<DataQualityWarnPageVo> qualityWarnPageVoPage = dataQualityService.getpageWarnlist(warnPageDto);
        return CommonResult.success(qualityWarnPageVoPage);
    }

    @ApiOperation(value = "获取任务名称")
    @GetMapping(value = "/getTaskList")
    public CommonResult<List<Map<String,String>>> getTaskList() {
        List<Map<String, String>> ruleList = dataQualityService.getTaskList();
        return CommonResult.success(ruleList);
    }
}
