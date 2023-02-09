package com.bigdata.backstage.modules.norm.mapper;

import com.bigdata.backstage.modules.norm.model.NormDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据标准-字典库 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
public interface NormDictMapper extends BaseMapper<NormDict> {

    List<Map<String,String>> getRuleList();
}
