package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetDataOverviewLabelRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetBindVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 数据资产标签映射表 Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Repository
public interface MetDataOverviewLabelRelationMapper extends BaseMapper<MetDataOverviewLabelRelation> {

    List<String> getOverviewIdList(String label);

    List<String> getLabelList(Long overviewId);

    List<String> getLabelIndexList(Long overviewId);

    List<DataAssetBindVo> getBindTblList(@Param("labelId") Long labelId);

    List<DataAssetBindVo> getAbleBindTblList(@Param("labelId") Long labelId, @Param("dwNameEn") String dwNameEn);

}
