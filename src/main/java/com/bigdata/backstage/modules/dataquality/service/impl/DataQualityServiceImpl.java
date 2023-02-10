package com.bigdata.backstage.modules.dataquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleTaskRelationMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityTaskMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityWarnMapper;
import com.bigdata.backstage.modules.common.model.*;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.dataquality.dto.RulePageDto;
import com.bigdata.backstage.modules.dataquality.dto.TaskPageDto;
import com.bigdata.backstage.modules.dataquality.dto.WarnPageDto;
import com.bigdata.backstage.modules.dataquality.service.DataQualityService;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityRulePageVo;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityTaskPageVo;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityWarnPageVo;
import com.bigdata.backstage.modules.norm.mapper.NormDictMapper;
import com.bigdata.backstage.modules.norm.model.NormDict;
import com.bigdata.backstage.modules.norm.service.NormDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataQualityServiceImpl implements DataQualityService {

    @Autowired
    private NormDictMapper normDictMapper;
    @Autowired
    private MetQualityRuleMapper metQualityRuleMapper;
    @Autowired
    private MetDwInfoService metDwInfoService;
    @Autowired
    private NormDictService normDictService;
    @Autowired
    private MetQualityRuleTaskRelationMapper qualityRuleTaskRelationMapper;
    @Autowired
    private MetQualityTaskMapper metQualityTaskMapper;
    @Autowired
    private MetQualityWarnMapper metQualityWarnMapper;

    @Override
    public IPage<DataQualityRulePageVo> pageQueryList(RulePageDto rulePageDto) {
        List<MetQualityRule> records = new ArrayList<>();
        IPage<DataQualityRulePageVo> dataQualityRulePageVos = new Page<>();
        Page<MetQualityRule> metQualityRulePage = metQualityRuleMapper.selectPage(new Page<>(rulePageDto.getCurrent(), rulePageDto.getPageSize()),
                new QueryWrapper<MetQualityRule>()
                        .eq(rulePageDto.getProjectId() != null, "dw_id", rulePageDto.getProjectId())
                        .like(rulePageDto.getRuleName() != null && !"".equals(rulePageDto.getRuleName()), "rule_name", rulePageDto.getRuleName())
                        .eq(rulePageDto.getRuleType() != null, "rule_type", rulePageDto.getRuleType())
                        .orderByDesc("create_time"));
        records = metQualityRulePage.getRecords();

        if (!records.isEmpty()) {
            ArrayList<DataQualityRulePageVo> dataQualityRulePage = new ArrayList<>();
            for (MetQualityRule record : records) {
                DataQualityRulePageVo rulePageVo = new DataQualityRulePageVo();
                BeanUtils.copyProperties(record, rulePageVo);
                MetDwInfo metDwInfo = metDwInfoService.getById(record.getDwId());
                rulePageVo.setProjectName(metDwInfo.getDwNameZn());
                NormDict normDict = normDictService.getById(record.getRuleType());
                rulePageVo.setRuleTypeName(normDict.getName());
//                Long count = qualityRuleTaskRelationMapper.selectCount(new QueryWrapper<MetQualityRuleTaskRelation>().eq("rule_id", record.getId()).eq("is_delete", 0));
                Long count = metQualityTaskMapper.selectCount(new QueryWrapper<MetQualityTask>().eq("rule_id", record.getId()).eq("is_delete", 0));
                rulePageVo.setRuleBindNum(count.intValue());
                dataQualityRulePage.add(rulePageVo);
            }
            dataQualityRulePageVos.setRecords(dataQualityRulePage);
            dataQualityRulePageVos.setTotal(metQualityRulePage.getTotal());
            dataQualityRulePageVos.setPages(metQualityRulePage.getPages());
            dataQualityRulePageVos.setSize(metQualityRulePage.getSize());
            dataQualityRulePageVos.setCurrent(metQualityRulePage.getCurrent());
        }
        return dataQualityRulePageVos;
    }

    @Override
    public List<Map<String, String>> getRuleList() {
        return normDictMapper.getRuleList();
    }

    @Override
    public IPage<DataQualityTaskPageVo> getpageTasklist(TaskPageDto taskPageDto) {
        List<MetQualityTask> records = new ArrayList<>();
        IPage<DataQualityTaskPageVo> dataQualityTaskPageVoPage = new Page<>();
        Page<MetQualityTask> metQualityTaskPage = metQualityTaskMapper.selectPage(new Page<>(taskPageDto.getCurrent(), taskPageDto.getPageSize()),
                new QueryWrapper<MetQualityTask>()
                        .eq(taskPageDto.getProjectId() != null, "dw_id", taskPageDto.getProjectId())
                        .like(taskPageDto.getTaskName() != null && !"".equals(taskPageDto.getTaskName()), "task_name", taskPageDto.getTaskName())
                        .eq(taskPageDto.getStatus() != null, "status", taskPageDto.getStatus())
                        .orderByDesc("create_time"));
        records = metQualityTaskPage.getRecords();
        if (!records.isEmpty()) {
            ArrayList<DataQualityTaskPageVo> dataQualityTaskPage = new ArrayList<>();
            for (MetQualityTask record : records) {
                DataQualityTaskPageVo taskPageVo = new DataQualityTaskPageVo();
                BeanUtils.copyProperties(record, taskPageVo);
                MetDwInfo metDwInfo = metDwInfoService.getById(record.getDwId());
                taskPageVo.setProjectName(metDwInfo.getDwNameZn());
                dataQualityTaskPage.add(taskPageVo);
            }
            dataQualityTaskPageVoPage.setRecords(dataQualityTaskPage);
            dataQualityTaskPageVoPage.setTotal(metQualityTaskPage.getTotal());
            dataQualityTaskPageVoPage.setPages(metQualityTaskPage.getPages());
            dataQualityTaskPageVoPage.setSize(metQualityTaskPage.getSize());
            dataQualityTaskPageVoPage.setCurrent(metQualityTaskPage.getCurrent());
        }
        return dataQualityTaskPageVoPage;
    }

    @Override
    public List<Map<String, String>> getBindRuleList() {
        return metQualityRuleMapper.getBindRuleList();
    }

    @Override
    public IPage<DataQualityWarnPageVo> getpageWarnlist(WarnPageDto warnPageDto) {
        List<MetQualityWarn> records = new ArrayList<>();
        IPage<DataQualityWarnPageVo> dataQualityRulePageVos = new Page<>();
        Page<MetQualityWarn> metQualityRulePage = metQualityWarnMapper.selectPage(new Page<>(warnPageDto.getCurrent(), warnPageDto.getPageSize()),
                new QueryWrapper<MetQualityWarn>()
                        .eq(warnPageDto.getProjectId() != null, "dw_id", warnPageDto.getProjectId())
                        .eq(warnPageDto.getTaskNameId() != null, "task_id", warnPageDto.getTaskNameId())
                        .orderByDesc("create_time"));
        records = metQualityRulePage.getRecords();

        if (!records.isEmpty()) {
            ArrayList<DataQualityWarnPageVo> dataQualityWarnPage = new ArrayList<>();
            for (MetQualityWarn record : records) {
                DataQualityWarnPageVo warnPageVo = new DataQualityWarnPageVo();
                BeanUtils.copyProperties(record, warnPageVo);
                MetDwInfo metDwInfo = metDwInfoService.getById(record.getDwId());
                warnPageVo.setProjectName(metDwInfo.getDwNameZn());
                NormDict normDict = normDictService.getById(record.getRuleType());
                warnPageVo.setRuleTypeName(normDict.getName());
                dataQualityWarnPage.add(warnPageVo);
            }
            dataQualityRulePageVos.setRecords(dataQualityWarnPage);
            dataQualityRulePageVos.setTotal(metQualityRulePage.getTotal());
            dataQualityRulePageVos.setPages(metQualityRulePage.getPages());
            dataQualityRulePageVos.setSize(metQualityRulePage.getSize());
            dataQualityRulePageVos.setCurrent(metQualityRulePage.getCurrent());
        }
        return dataQualityRulePageVos;
    }

    @Override
    public List<Map<String, String>> getTaskList() {
        return metQualityTaskMapper.getTaskList();
    }
}
