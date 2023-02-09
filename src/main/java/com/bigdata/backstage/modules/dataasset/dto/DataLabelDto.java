package com.bigdata.backstage.modules.dataasset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class DataLabelDto {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "所属项目")
    private String labelName;
}
