package com.bigdata.backstage.modules.norm.mapper;

import com.bigdata.backstage.modules.norm.model.NormRoot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 数据标准-字根库 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */

@Repository
@Mapper
public interface NormRootMapper extends BaseMapper<NormRoot> {

}
