package com.bigdata.backstage.modules.source.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataSourceInfoDto {
    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("表名")
    private String tableName;
}
