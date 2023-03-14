package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetQualityTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据质量-质检任务 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
public interface MetQualityTaskMapper extends BaseMapper<MetQualityTask> {


    List<Map<String,String>> getTaskList(@Param("dwId") Integer dwId) ;
}
