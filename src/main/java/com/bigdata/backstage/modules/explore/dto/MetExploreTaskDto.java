package com.bigdata.backstage.modules.explore.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据勘探-探查新增", description = "MetExploreTaskDto传输对象")
public class MetExploreTaskDto {

    @ApiModelProperty("勘探的表名")
    private String total;

    @ApiModelProperty("创建人")
    private String createUser;
}
