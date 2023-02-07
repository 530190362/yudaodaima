package com.bigdata.backstage.modules.dataasset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class DataAssetDto {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "表英文名")
    private String tableName;

    @ApiModelProperty(value = "表中文名")
    private String tableComment;
    @ApiModelProperty(value = "表层级")
    private String tblLevel;

    @ApiModelProperty(value = "表标签")
    private String label;

    }
