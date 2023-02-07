package com.bigdata.backstage.modules.dataasset.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.model.MetDataLabel;
import com.bigdata.backstage.modules.common.model.MetDataOverview;
import com.bigdata.backstage.modules.common.service.MetDataLabelService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewService;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetDetailVo;
import com.bigdata.backstage.modules.dataasset.vo.DataAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectAssetVo;
import com.bigdata.backstage.modules.dataasset.vo.DataSelectLabelVo;
import com.bigdata.backstage.modules.norm.model.NormDict;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MetDataLabelService metDataLabelService;

    @ApiOperation(value = "数据资产分页查询")
    @PostMapping("/pagelsit")
    public CommonResult<IPage<DataAssetVo>> businessList(@RequestBody DataAssetDto dataAssetDto) {
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

    @ApiOperation(value = "数据资产详情")
    @GetMapping(value = "/getDataAssetById")
    public CommonResult<DataAssetDetailVo> queryDataAssetById(Integer assetId) {
        MetDataOverview metOverview = metDataOverviewService.getById(assetId);
        List<String> labelList = new ArrayList<>();
        if (!metOverview.getLabel().isEmpty()) {
            String[] split = metOverview.getLabel().split(",");
            for (String s : split) {
                MetDataLabel byId = metDataLabelService.getById(s);
                labelList.add(byId.getLabelName());
            }
        }
        DataAssetDetailVo dataAssetDetailVo = BeanUtil.copyProperties(metOverview, DataAssetDetailVo.class);
        dataAssetDetailVo.setLabel(labelList.toString());
        return CommonResult.success(dataAssetDetailVo);
    }
}
