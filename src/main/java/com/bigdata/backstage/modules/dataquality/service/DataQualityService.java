package com.bigdata.backstage.modules.dataquality.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetVo;
import com.bigdata.backstage.modules.dataquality.dto.RulePageDto;
import com.bigdata.backstage.modules.dataquality.vo.DataQualityRulePageVo;

import java.util.List;
import java.util.Map;

public interface DataQualityService {

    IPage<DataQualityRulePageVo> pageQueryList(RulePageDto rulePageDto);

    List<Map<String,String>> getRuleList() ;
}
