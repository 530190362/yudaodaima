package com.bigdata.backstage.modules.dataasset.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.mapper.MetDataOverviewLabelRelationMapper;
import com.bigdata.backstage.modules.common.mapper.MetDataOverviewMapper;
import com.bigdata.backstage.modules.common.model.MetDataLabel;
import com.bigdata.backstage.modules.common.model.MetDataOverview;
import com.bigdata.backstage.modules.common.model.MetDataOverviewLabelRelation;
import com.bigdata.backstage.modules.common.service.MetDataLabelService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewLabelRelationService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewService;
import com.bigdata.backstage.modules.dataasset.dto.BindLabelDto;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.*;
import com.bigdata.backstage.modules.norm.model.NormDict;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据资产
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/dataasset")
public class DataAssetController {

    @Autowired
    private DataAssetService dataAssetService;
    @Autowired
    private MetDataOverviewService metDataOverviewService;
    @Autowired
    private MetDataOverviewMapper metDataOverviewMapper;
    @Autowired
    private MetDataLabelService metDataLabelService;
    @Autowired
    private MetDataOverviewLabelRelationMapper metDataOverviewLabelRelationMapper;
    @Autowired
    private MetDataOverviewLabelRelationService metDataOverviewLabelRelationService;

    @ApiOperation(value = "数据资产分页查询")
    @PostMapping("/pagelist")
    public CommonResult<IPage<DataAssetVo>> pageAssetList(@RequestBody DataAssetDto dataAssetDto) {
        IPage<DataAssetVo> dataAssetVoPage = dataAssetService.pageQueryList(dataAssetDto);
        return CommonResult.success(dataAssetVoPage);
    }

    @ApiOperation(value = "数据标签列表")
    @GetMapping(value = "/getDataSelectLabel")
    public CommonResult<List<DataSelectLabelVo>> queryDataSelectLabel() {
        List<DataSelectLabelVo> dataSelectLabelVos = dataAssetService.queryDataSelectLabel();
        return CommonResult.success(dataSelectLabelVos);
    }

    @ApiOperation(value = "数据资产列表")
    @GetMapping(value = "/getDataSelectAsset")
    public CommonResult<List<DataSelectAssetVo>> queryDataSelectAsset() {
        List<DataSelectAssetVo> areaNameList = dataAssetService.queryDataSelectAsset();
        return CommonResult.success(areaNameList);
    }

    @ApiOperation(value = "数据仓名称列表")
    @GetMapping(value = "/getDataSelectDw")
    public CommonResult<List<DataSelectDwVo>> queryDataSelectDw() {
        List<DataSelectDwVo> areaNameList = dataAssetService.queryDataSelectDw();
        return CommonResult.success(areaNameList);
    }

    @ApiOperation(value = "数据资产详情")
    @GetMapping(value = "/getDataAssetById")
    public CommonResult<DataAssetDetailVo> queryDataAssetById(Integer assetId) {
        MetDataOverview metOverview = metDataOverviewService.getById(assetId);
        List<String> overviewIdList = metDataOverviewLabelRelationMapper.getLabelList(metOverview.getId());
//        List<String> labelList = new ArrayList<>();
//        if (!metOverview.getLabel().isEmpty()) {
//            String[] split = metOverview.getLabel().split(",");
//            for (String s : split) {
//                MetDataLabel byId = metDataLabelService.getById(s);
//                labelList.add(byId.getLabelName());
//            }
//        }
        DataAssetDetailVo dataAssetDetailVo = BeanUtil.copyProperties(metOverview, DataAssetDetailVo.class);
        dataAssetDetailVo.setLabel(overviewIdList.toString());
        return CommonResult.success(dataAssetDetailVo);
    }

    @ApiOperation(value = "字段信息查询")
    @GetMapping(value = "/getDataField")
    public CommonResult<List<DataFiledVo>> queryDataField(String tblName) {
        List<DataFiledVo> dataFiledVos = dataAssetService.queryDataField(tblName);
        return CommonResult.success(dataFiledVos);
    }

    @ApiOperation(value = "根据表id查询其标签")
    @GetMapping(value = "/getLabelList")
    public CommonResult<List<String>> queryDataField(Long overviewId) {
        List<String> labelList = metDataOverviewLabelRelationMapper.getLabelList(overviewId);
        return CommonResult.success(labelList);
    }

    @ApiOperation(value = "表标签绑定")
    @PostMapping("/update")
    public CommonResult update(@RequestBody BindLabelDto bindLabel) {
        int insert = 0;
        if (null != bindLabel.getLabelIds()) {
            for (Integer labelId : bindLabel.getLabelIds()) {
                List<MetDataOverviewLabelRelation> list = metDataOverviewLabelRelationService.list(new LambdaQueryWrapper<MetDataOverviewLabelRelation>()
                        .eq(MetDataOverviewLabelRelation::getOverviewId, bindLabel.getOverviewId())
                        .eq(MetDataOverviewLabelRelation::getLabelId, labelId)
                );
                if (CollectionUtils.isEmpty(list)){
                    MetDataOverviewLabelRelation mapping = new MetDataOverviewLabelRelation();
                    mapping.setLabelId(labelId);
                    mapping.setOverviewId(bindLabel.getOverviewId());
                    insert = metDataOverviewLabelRelationMapper.insert(mapping);
                }
            }
        }
        if (insert>0){
            return CommonResult.success(null);
        }else {
            return CommonResult.failed("绑定失败");
        }
    }

    @ApiOperation(value = "标签分页查询")
    @PostMapping("/labelpagelsit")
    public CommonResult<IPage<DataLabelVo>> pageLabelList(@RequestBody DataAssetDto dataAssetDto) {
//        IPage<DataAssetVo> dataAssetVoPage = dataAssetService.labelPageQueryList(dataAssetDto);
//        return CommonResult.success(dataAssetVoPage);
        return null;
    }
}
