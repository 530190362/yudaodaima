package com.bigdata.backstage.modules.dataasset.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigdata.backstage.common.api.CommonResult;
import com.bigdata.backstage.modules.common.mapper.MetDataOverviewLabelRelationMapper;
import com.bigdata.backstage.modules.common.model.MetDataLabel;
import com.bigdata.backstage.modules.common.model.MetDataOverview;
import com.bigdata.backstage.modules.common.model.MetDataOverviewLabelRelation;
import com.bigdata.backstage.modules.common.service.MetDataLabelService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewLabelRelationService;
import com.bigdata.backstage.modules.common.service.MetDataOverviewService;
import com.bigdata.backstage.modules.dataasset.dto.BindLabelDto;
import com.bigdata.backstage.modules.dataasset.dto.BindTblDto;
import com.bigdata.backstage.modules.dataasset.dto.DataAssetDto;
import com.bigdata.backstage.modules.dataasset.dto.DataLabelDto;
import com.bigdata.backstage.modules.dataasset.service.DataAssetService;
import com.bigdata.backstage.modules.dataasset.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        List<String> labelIndexList = metDataOverviewLabelRelationMapper.getLabelIndexList(metOverview.getId());
        DataAssetDetailVo dataAssetDetailVo = BeanUtil.copyProperties(metOverview, DataAssetDetailVo.class);
        dataAssetDetailVo.setTblSize(metOverview.getTblSize().divide(BigDecimal.valueOf(1000),2, RoundingMode.UP));
        dataAssetDetailVo.setLabelIndex(labelIndexList);
        dataAssetDetailVo.setLabel(overviewIdList);
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
    public CommonResult<List<String>> queryLabelList(Long overviewId) {
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
            return CommonResult.success("绑定成功");
        }else {
            return CommonResult.failed("绑定失败");
        }
    }

    @ApiOperation(value = "标签分页查询")
    @PostMapping("/labelpagelsit")
    public CommonResult<IPage<DataLabelVo>> pageLabelList(@RequestBody DataLabelDto dataLabelDto) {
        IPage<DataLabelVo> dataAssetVoPage = dataAssetService.labelPageQuery(dataLabelDto);
        return CommonResult.success(dataAssetVoPage);
    }

    @ApiOperation(value = "新增修改")
    @PostMapping(value = "/addOrUpdate")
    public CommonResult addOrUpdate(@RequestBody MetDataLabel metDataLabel) {
        if (metDataLabel.getId() == null){
            boolean save = metDataLabelService.save(metDataLabel);
            if (save) {
                return CommonResult.success("新增成功");
            } else {
                return CommonResult.failed("新增失败");
            }
        }else {
            boolean update = metDataLabelService.updateById(metDataLabel);
            if (update) {
                return CommonResult.success("修改成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        Long tblNum = metDataOverviewLabelRelationMapper.selectCount(new QueryWrapper<MetDataOverviewLabelRelation>()
                .eq("label_id", id).eq("is_delete", 0));
        if (tblNum > 0) {
            return CommonResult.failed("删除失败,标签下有关联表");
        }
        boolean delete = metDataLabelService.removeById(id);
        if (delete) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation(value = "根据标签id查询关联表")
    @GetMapping(value = "/getTblListById")
    public CommonResult<UnionTblVo> queryTblListById(Long labelId) {
        UnionTblVo unionTblVo = new UnionTblVo();
        MetDataLabel metDataLabel = metDataLabelService.getById(labelId);
        Long bindNum = metDataOverviewLabelRelationMapper.selectCount(new QueryWrapper<MetDataOverviewLabelRelation>()
                .eq("label_id", labelId).eq("is_delete", 0));
        List<DataAssetBindVo> labelList = metDataOverviewLabelRelationMapper.getBindTblList(labelId);
        List<DataAssetBindVo> ableBindTblList = metDataOverviewLabelRelationMapper.getAbleBindTblList(labelId);
        unionTblVo.setLabelName(metDataLabel.getLabelName());
        unionTblVo.setBindNum(bindNum.intValue());
        unionTblVo.setAbleBindTblList(ableBindTblList);
        unionTblVo.setHasBindTblList(labelList);
        return CommonResult.success(unionTblVo);
    }

    @ApiOperation(value = "标签绑定表")
    @PostMapping("/labelBindTbl")
    public CommonResult labelBindTbl(@RequestBody BindTblDto bindTbl) {
        int insert = 0;
        if (null != bindTbl.getOverviewIds()) {
            for (Integer tblId : bindTbl.getOverviewIds()) {
                List<MetDataOverviewLabelRelation> list = metDataOverviewLabelRelationService.list(new LambdaQueryWrapper<MetDataOverviewLabelRelation>()
                        .eq(MetDataOverviewLabelRelation::getLabelId,bindTbl.getLabelId() )
                        .eq(MetDataOverviewLabelRelation::getOverviewId, tblId)
                );
                if (CollectionUtils.isEmpty(list)){
                    MetDataOverviewLabelRelation mapping = new MetDataOverviewLabelRelation();
                    mapping.setLabelId(bindTbl.getLabelId());
                    mapping.setOverviewId(tblId);
                    insert = metDataOverviewLabelRelationMapper.insert(mapping);
                }
            }
        }
        if (insert>0){
            return CommonResult.success("绑定成功");
        }else {
            return CommonResult.failed("绑定失败");
        }
    }
}
