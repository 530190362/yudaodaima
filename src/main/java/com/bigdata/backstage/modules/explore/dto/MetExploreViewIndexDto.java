package com.bigdata.backstage.modules.explore.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据勘探-探查概览指标", description = "MetExploreViewIndexDto传输对象")
public class MetExploreViewIndexDto {
    @ApiModelProperty("总探查数据记录数")
    private Long totalRows;

    @ApiModelProperty("总探查表数量")
    private Long totalTables;

    @ApiModelProperty("总探查字段数")
    private Long totalColumns;

    @ApiModelProperty("当日探查数据记录数")
    private Long todayRows;

    @ApiModelProperty("当日探查表数量")
    private Long todayTables;

    @ApiModelProperty("当日探查字段数")
    private Long todayColumns;
}
