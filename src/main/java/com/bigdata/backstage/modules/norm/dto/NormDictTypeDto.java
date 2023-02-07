package com.bigdata.backstage.modules.norm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NormDictTypeDto {
    @ApiModelProperty(value = "id")
    private Long id;

    //字典值
    @ApiModelProperty(value = "字典值")
    private String name;
}
