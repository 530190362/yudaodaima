package com.bigdata.backstage.modules.explore.mapper;

import com.bigdata.backstage.modules.explore.dto.MetExploreViewHistoryDto;
import com.bigdata.backstage.modules.explore.dto.MetExploreViewIndexDto;
import com.bigdata.backstage.modules.explore.model.MetExploreTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据勘探-数据勘探操作表 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
public interface MetExploreTaskMapper extends BaseMapper<MetExploreTask> {


    List<MetExploreViewHistoryDto> selectHistory(@Param("limit") Integer limit,@Param("dwId") Integer dwId);

    MetExploreViewIndexDto selectTotal(@Param("dwId") Integer dwId);

    MetExploreViewIndexDto selectToday(@Param("dwId") Integer dwId);
}
