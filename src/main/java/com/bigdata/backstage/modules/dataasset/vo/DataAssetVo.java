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
@ApiModel(value = "数据资产分页查询展示对象", description = "数据资产分页查询展示对象")
public class DataAssetVo {

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

    @ApiModelProperty("表类型 内部表/外部表")
    private String tblType;

    @ApiModelProperty("表大小 单位:MB")
    private BigDecimal tblSize;

    @ApiModelProperty("标签")
    private String label;

}
