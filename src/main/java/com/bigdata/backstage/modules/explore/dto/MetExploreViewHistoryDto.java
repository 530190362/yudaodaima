package com.bigdata.backstage.modules.explore.dto;

import com.google.common.collect.Tables;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "数据勘探-探查概览历史指标", description = "MetExploreViewHistoryDto传输对象")
public class MetExploreViewHistoryDto {

    @ApiModelProperty("日期 yyyy-MM-dd")
    private String date;

    @ApiModelProperty("探查数据记录数（条）")
    private Long rows;

    @ApiModelProperty("探查表数量（张）")
    private Long tables;

    @ApiModelProperty("探查字段数")
    private Long columns;

}
