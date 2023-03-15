package com.bigdata.backstage.modules.dataasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.backstage.modules.common.mapper.*;
import com.bigdata.backstage.modules.common.model.*;
import com.bigdata.backstage.modules.common.service.MetDwInfoService;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.dto.DataLabelDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataAssetServiceImpl implements DataAssetService {

    @Autowired
    private MetDataTableMapper dataTableMapper;
    @Autowired
    private MetDataLabelMapper dataLabelMapper;
    @Autowired
    private ViewMetDetailOutlineMapper metDetailOutlineMapper;
    @Autowired
    private MetDwInfoMapper metDwInfoMapper;
    @Autowired
    private MetDwInfoService metDwInfoService;
    @Autowired
    private MetDataOverviewLabelRelationMapper metDataOverviewLabelRelationMapper;

    @Override
    public IPage<DataAssetVo> pageQueryList(DataAssetDto dataAssetDto) {
//        List<MetDataOverview> records = new ArrayList<>();
        List<MetDataTable> records = new ArrayList<>();
        IPage<DataAssetVo> dataAssetVoPage = new Page<>();
        Page<MetDataTable> dataOverviewPage = new Page<>();
        if (dataAssetDto.getLabel() == null) {
            dataOverviewPage = dataTableMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                    new QueryWrapper<MetDataTable>()
                            .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "tbl_name", dataAssetDto.getTableName())
                            .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "tbl_comment", dataAssetDto.getTableComment())
                            .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                            .eq("dw_id", dataAssetDto.getDwId())
                            .orderByAsc("create_time"));
            records = dataOverviewPage.getRecords();
        } else {
            List<String> overviewIdList = metDataOverviewLabelRelationMapper.getOverviewIdList(dataAssetDto.getLabel());
            if (overviewIdList.size() > 0) {
                dataOverviewPage = dataTableMapper.selectPage(new Page<>(dataAssetDto.getCurrent(), dataAssetDto.getPageSize()),
                        new QueryWrapper<MetDataTable>()
                                .like(dataAssetDto.getTableName() != null && !"".equals(dataAssetDto.getTableName()), "table_name", dataAssetDto.getTableName())
                                .like(dataAssetDto.getTableComment() != null && !"".equals(dataAssetDto.getTableComment()), "table_comment", dataAssetDto.getTableComment())
                                .eq(dataAssetDto.getTblLevel() != null, "tbl_level", dataAssetDto.getTblLevel())
                                .eq(dataAssetDto.getProjectName() != null, "dw_id", dataAssetDto.getProjectName())
                                .in(overviewIdList != null, "id", overviewIdList).orderByAsc("create_time"));
                records = dataOverviewPage.getRecords();
            }
        }
        if (!records.isEmpty()) {
            ArrayList<DataAssetVo> dataAssetVos = new ArrayList<>();
            for (MetDataTable record : records) {
                DataAssetVo dataAssetVo = new DataAssetVo();
                BeanUtils.copyProperties(record, dataAssetVo);
                dataAssetVo.setTblSize(record.getTblSize().setScale(2, RoundingMode.HALF_UP));
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
    public List<DataSelectLabelVo> queryDataSelectLabel(String dwNameEn) {
        return dataLabelMapper.queryDataSelectLabel(dwNameEn);
    }

    @Override
    public List<DataSelectAssetVo> queryDataSelectAsset() {
        return dataLabelMapper.queryDataSelectAsset();
    }

    @Override
    public List<DataSelectDwVo> queryDataSelectDw(Integer dwId) {
        return metDwInfoMapper.queryDataSelectDw(dwId);
    }


    @Override
    public List<DataFiledVo> queryDataField(String tblName,Integer dwId) {
        return metDetailOutlineMapper.queryDataField(tblName,dwId);
    }

    @Override
    public IPage<DataLabelVo> labelPageQuery(DataLabelDto dataLabelDto) {
        Page<MetDataLabel> labelPage = dataLabelMapper.selectPage(new Page<>(dataLabelDto.getCurrent(), dataLabelDto.getPageSize()), new QueryWrapper<MetDataLabel>()
                .eq(dataLabelDto.getLabelId() != null, "id", dataLabelDto.getLabelId())
                .eq( "create_user", dataLabelDto.getCreateUser())
                .orderByDesc("update_time"));
        List<MetDataLabel> records = labelPage.getRecords();
        IPage<DataLabelVo> dataLabelVoPage = new Page<>();
        if (!records.isEmpty()) {
            ArrayList<DataLabelVo> dataLabelVos = new ArrayList<>();
            for (MetDataLabel record : records) {
                DataLabelVo dataLabelVo = new DataLabelVo();
                BeanUtils.copyProperties(record, dataLabelVo);
                Long tblNum = metDataOverviewLabelRelationMapper.selectCount(new QueryWrapper<MetDataOverviewLabelRelation>()
                        .eq("label_id", record.getId()).eq("is_delete", 0));
                dataLabelVo.setRelationTblNum(tblNum.intValue());
                dataLabelVos.add(dataLabelVo);
            }
            dataLabelVoPage.setRecords(dataLabelVos);
            dataLabelVoPage.setTotal(labelPage.getTotal());
            dataLabelVoPage.setPages(labelPage.getPages());
            dataLabelVoPage.setSize(labelPage.getSize());
            dataLabelVoPage.setCurrent(labelPage.getCurrent());
        }
        return dataLabelVoPage;
    }


}
