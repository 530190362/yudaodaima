package com.bigdata.backstage.modules.dataasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.mapper.*;
import com.bigdata.backstage.modules.common.model.MetDataLabel;
import com.bigdata.backstage.modules.common.model.MetDataOverview;
import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.bigdata.backstage.modules.common.service.MetDataLabelService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewLabelRelationService;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.*;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private ViewMetDetailOutlineMapper metDetailOutlineMapper;
    @Autowired
    private MetDataLabelService metDataLabelService;
    @Autowired
    private MetDwInfoMapper metDwInfoMapper;
    @Autowired
    private MetDwInfoService metDwInfoService;
    @Autowired
    private MetDataOverviewLabelRelationService metDataOverviewLabelRelationService;
    @Autowired
    private MetDataOverviewLabelRelationMapper metDataOverviewLabelRelationMapper;

    @Override
    public IPage<DataAssetVo> pageQueryList(DataAssetDto dataAssetDto) {
        List<MetDataOverview> records = new ArrayList<>();
        IPage<DataAssetVo> dataAssetVoPage = new Page<>();
        Page<MetDataOverview> dataOverviewPage = new Page<>();
        if (dataAssetDto.getLabel()==null) {
            dataOverviewPage = dataOverviewMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                    new QueryWrapper<MetDataOverview>()
                            .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                            .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                            .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                            .eq(dataAssetDto.getProjectName() !=null ,"dw_id",dataAssetDto.getProjectName())
                            .orderByAsc("create_time"));
            records = dataOverviewPage.getRecords();
        }else {
            List<String> overviewIdList = metDataOverviewLabelRelationMapper.getOverviewIdList(dataAssetDto.getLabel());
            dataOverviewPage = dataOverviewMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                    new QueryWrapper<MetDataOverview>()
                            .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                            .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                            .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                            .eq(dataAssetDto.getProjectName() !=null ,"dw_id",dataAssetDto.getProjectName())
                            .in(overviewIdList != null, "id", overviewIdList).orderByAsc("create_time"));
            records = dataOverviewPage.getRecords();
        }
        if (!records.isEmpty()) {
            ArrayList<DataAssetVo> dataAssetVos = new ArrayList<>();
            for (MetDataOverview record : records) {
                DataAssetVo dataAssetVo = new DataAssetVo();
                BeanUtils.copyProperties(record, dataAssetVo);
                dataAssetVo.setTblSize(record.getTblSize().divide(BigDecimal.valueOf(1000),2, RoundingMode.UP));
                MetDwInfo metDwInfo = metDwInfoService.getById(record.getDwId());
                dataAssetVo.setProjectName(metDwInfo.getDwNameZn());
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

    @Override
    public List<DataSelectDwVo> queryDataSelectDw() {
        return metDwInfoMapper.queryDataSelectDw();
    }


    @Override
    public List<DataFiledVo> queryDataField(String tblName) {
        return metDetailOutlineMapper.queryDataField(tblName);
    }



    //////////////////////////
    public IPage<DataAssetVo> pageQueryList1(DataAssetDto dataAssetDto) {
        List<MetDataOverview> records = new ArrayList<>();
        IPage<DataAssetVo> dataAssetVoPage = new Page<>();
        Page<MetDataOverview> dataOverviewPage = new Page<>();
        if (dataAssetDto.getLabel().isEmpty()) {
            dataOverviewPage = dataOverviewMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                    new QueryWrapper<MetDataOverview>()
                            .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                            .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                            .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                            .eq(dataAssetDto.getProjectName() !=null ,"dw_id",dataAssetDto.getProjectName())
                            .orderByAsc("create_time"));
            records = dataOverviewPage.getRecords();
        }else {
            List<String> overviewIdList = metDataOverviewLabelRelationMapper.getOverviewIdList(dataAssetDto.getLabel());
            dataOverviewPage = dataOverviewMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                    new QueryWrapper<MetDataOverview>()
                            .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                            .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                            .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                            .eq(dataAssetDto.getProjectName() !=null ,"dw_id",dataAssetDto.getProjectName())
                            .in(overviewIdList != null, "id", overviewIdList).orderByAsc("create_time"));
            records = dataOverviewPage.getRecords();
        }
        if (!records.isEmpty()) {
            ArrayList<DataAssetVo> dataAssetVos = new ArrayList<>();
            for (MetDataOverview record : records) {
                DataAssetVo dataAssetVo = new DataAssetVo();
                BeanUtils.copyProperties(record, dataAssetVo);
                dataAssetVo.setTblSize(record.getTblSize().divide(BigDecimal.valueOf(1000),2, RoundingMode.UP));
                MetDwInfo metDwInfo = metDwInfoService.getById(record.getProjectName());
                dataAssetVo.setProjectName(metDwInfo.getDwNameZn());
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

}