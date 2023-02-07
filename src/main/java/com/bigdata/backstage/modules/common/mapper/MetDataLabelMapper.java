package com.bigdata.backstage.modules.common.mapper;

import com.bigdata.backstage.modules.common.model.MetDataLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectLabelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 数据资产标签表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-02-07
 */
@Repository
public interface MetDataLabelMapper extends BaseMapper<MetDataLabel> {

    List<DataSelectLabelVo> queryDataSelectLabel();

    List<DataSelectAssetVo> queryDataSelectAsset();
}
