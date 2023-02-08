package com.bigdata.backstage.modules.dataasset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BindLabelDto {

    @ApiModelProperty(value = "表id")
    private Integer overviewId;

    @ApiModelProperty(value = "标签id")
    private List<Integer> labelIds;
}
