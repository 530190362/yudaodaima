package com.bigdata.backstage.modules.dataasset.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据资产详情查询展示对象", description = "数据资产详情查询展示对象")
public class DataAssetDetailVo {
    private Long id;


    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("表层级")
    private String tblLevel;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表备注")
    private String tableComment;

    @ApiModelProperty("字段数")
    private Integer tblColNum;

    @ApiModelProperty("表创建时间")
    private Date tblCreateTime;

    @ApiModelProperty("表更新时间")
    private Date tblUpdateTime;

    @ApiModelProperty("表大小 单位:MB")
    private BigDecimal tblSize;

    @ApiModelProperty("是否是分区表 1-是 0-否")
    private Boolean isPt;

    @ApiModelProperty("表一级明细")
    private String index1Name;

    @ApiModelProperty("表二级明细")
    private String index2Name;

    @ApiModelProperty("表三级明细")
    private String index3Name;

    @ApiModelProperty("表类型 内部表/外部表")
    private String tblType;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("是否删除  0:未删除 1:已删除")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("表生命周期")
    private Integer tblLifecycle;

    @ApiModelProperty("最新分区")
    private String ptLast;


}
