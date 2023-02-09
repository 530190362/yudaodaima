package com.bigdata.backstage.modules.source.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据集成-集成概览(指标)", description = "DataSourceTotalDto传输对象")
public class DataSourceTotalDto {

    @ApiModelProperty("数据表总数（张）")
    private Long tables;

    @ApiModelProperty("占用空间（GB）")
    private Double size;

    @ApiModelProperty("总数据记录数（条）")
    private Long rows;


}
