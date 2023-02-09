package com.bigdata.backstage.modules.dataasset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.dto.DataLabelDto;
import com.bigdata.backstage.modules.dataasset.vo.*;

import java.util.List;

public interface DataAssetService {

    IPage<DataAssetVo> pageQueryList(DataAssetDto dataAssetDto);

    List<DataSelectLabelVo> queryDataSelectLabel();

    List<DataSelectAssetVo> queryDataSelectAsset();

    List<DataSelectDwVo> queryDataSelectDw();

    /**
     * 查询表字段
     * @param tblName
     * @return
     */
    List<DataFiledVo> queryDataField(String tblName);

    IPage<DataLabelVo> labelPageQuery(DataLabelDto dataLabelDto);

}
