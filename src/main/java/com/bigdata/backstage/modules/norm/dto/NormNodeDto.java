package com.bigdata.backstage.modules.norm.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NormNodeDto传输对象", description = "数据标准-节点")
public class NormNodeDto extends PageDto {


    @ApiModelProperty("节点类型/名称")
    private String name;

    @ApiModelProperty("节点命名规范")
    private String rule;

    @ApiModelProperty("节点描述")
    private String nodeDesc;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;
}
