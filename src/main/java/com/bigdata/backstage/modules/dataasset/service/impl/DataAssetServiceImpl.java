package com.bigdata.backstage.modules.dataasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.mapper.MetDataLabelMapper;
import com.bigdata.backstage.modules.common.mapper.MetDataOverviewMapper;
import com.bigdata.backstage.modules.common.model.MetDataOverview;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataAssetServiceImpl implements DataAssetService {
    @Autowired
    private MetDataOverviewMapper dataOverviewMapper;
    @Autowired
    private MetDataLabelMapper dataLabelMapper;

    @Override
    public IPage<DataAssetVo> pageQueryList(DataAssetDto dataAssetDto) {
        Page<MetDataOverview> dataOverviewPage = dataOverviewMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                new QueryWrapper<MetDataOverview>()
                        .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                        .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                        .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                        .in(dataAssetDto.getLabel() != null && !"".equals(dataAssetDto.getLabel()), "label", dataAssetDto.getLabel()).orderByAsc("create_time"));
        List<MetDataOverview> records = dataOverviewPage.getRecords();
        IPage<DataAssetVo> dataAssetVoPage = new Page<>();
        if (!records.isEmpty()) {
            ArrayList<DataAssetVo> dataAssetVos = new ArrayList<>();
            for (MetDataOverview record : records) {
                DataAssetVo dataAssetVo = new DataAssetVo();
                dataAssetVo.setProjectName(record.getProjectName());
                dataAssetVo.setTblLevel(record.getTblLevel());
                dataAssetVo.setTableName(record.getTableName());
                dataAssetVo.setTableComment(record.getTableComment());
                dataAssetVo.setTblColNum(record.getTblColNum());
                dataAssetVo.setTblCreateTime(record.getTblCreateTime());
                dataAssetVo.setTblUpdateTime(record.getTblUpdateTime());
                dataAssetVo.setTblType(record.getTblType());
                dataAssetVo.setTblSize(record.getTblSize().divide(BigDecimal.valueOf(1000),2, RoundingMode.UP));
                dataAssetVos.add(dataAssetVo);
            }
            dataAssetVoPage.setRecords(dataAssetVos);
            dataAssetVoPage.setTotal(dataOverviewPage.getTotal());
            dataAssetVoPage.setPages(dataOverviewPage.getPages());
            dataAssetVoPage.setSize(dataOverviewPage.getSize());
            dataAssetVoPage.setCurrent(dataOverviewPage.getCurrent());
        }
        return dataAssetVoPage;
    }

    @Override
    public List<DataSelectLabelVo> queryDataSelectLabel() {
        return dataLabelMapper.queryDataSelectLabel() ;
    }

    @Override
    public List<DataSelectAssetVo> queryDataSelectAsset() {
        return dataLabelMapper.queryDataSelectAsset();
    }
}
