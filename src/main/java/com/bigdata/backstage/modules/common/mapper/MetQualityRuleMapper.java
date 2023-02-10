package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetQualityRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据质量-质检规则 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
public interface MetQualityRuleMapper extends BaseMapper<MetQualityRule> {

    List<Map<String,String>> getBindRuleList();
}
