package com.bigdata.backstage.modules.norm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NormTableDto extends PageDto {

    @ApiModelProperty("小层层级名称")
    private String level;

    @ApiModelProperty("命名标准版")
    private String rule;

    @ApiModelProperty("生命周期")
    private String lifecycle;

    @ApiModelProperty("描述")
    private String tableDesc;

    @ApiModelProperty("示例中文名称")
    private String exampleNameZh;

    @ApiModelProperty("示例英文名称")
    private String exampleNameEn;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

}
