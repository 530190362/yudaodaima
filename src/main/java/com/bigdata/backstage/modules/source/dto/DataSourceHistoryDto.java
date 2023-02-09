package com.bigdata.backstage.modules.source.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据集成-集成概览(折线图)", description = "DataSourceHistoryDto传输对象")
public class DataSourceHistoryDto {

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("数据集成只需要ODS层数据表")
    private Long ods;

    @ApiModelProperty("来源IRS（条）")
    private Long irs;

    @ApiModelProperty("来源业务系统（条）")
    private Long bms;
}
