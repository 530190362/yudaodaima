package com.bigdata.backstage.modules.ass.mapper;

import com.bigdata.backstage.modules.ass.model.AssSolution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 解决方案落地经验库 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-01-26
 */
@Repository
@Mapper
public interface AssSolutionMapper extends BaseMapper<AssSolution> {

}
