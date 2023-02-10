package com.bigdata.backstage.modules.dataquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityRuleTaskRelationMapper;
import com.bigdata.backstage.modules.common.mapper.MetQualityTaskMapper;
import com.bigdata.backstage.modules.common.model.*;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.dataquality.dto.RulePageDto;
import com.bigdata.backstage.modules.dataquality.service.DataQualityService;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityRulePageVo;
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
                rulePageVo.setRuleType(normDict.getName());
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
}
