package com.bigdata.backstage.modules.explore.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据勘探-勘探报告(表信息)", description = "MetExploreReportTableDto传输对象")
public class MetExploreReportTableDto {

    @ApiModelProperty("数仓id")
    private Long dwId;


    @ApiModelProperty("表名")
    private String tableName;
}
