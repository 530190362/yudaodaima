package com.bigdata.backstage.modules.dataasset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectLabelVo;

import java.util.List;

public interface DataAssetService {

    IPage<DataAssetVo> pageQueryList(DataAssetDto dataAssetDto);

    List<DataSelectLabelVo> queryDataSelectLabel();

    List<DataSelectAssetVo> queryDataSelectAsset();


}
