package com.bigdata.backstage.modules.dataasset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BindTblDto {

    @ApiModelProperty(value = "标签id")
    private Integer labelId;

    @ApiModelProperty(value = "表id")
    private List<Integer> overviewIds;
}
