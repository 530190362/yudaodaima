package com.bigdata.backstage.modules.norm.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NormRootDto {
    @ApiModelProperty("词根中文")
    private String nameZh;

    @ApiModelProperty("词根描述")
    private String rootDesc;

    @ApiModelProperty("词根英文")
    private String nameEn;

    @ApiModelProperty("词根英文简写")
    private String nameShortEn;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

}
