package com.bigdata.backstage.modules.dataquality.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskUpdateDto {

    private Long id;

    @ApiModelProperty("状态 1-上线 0-下线")
    private Integer status;



}
