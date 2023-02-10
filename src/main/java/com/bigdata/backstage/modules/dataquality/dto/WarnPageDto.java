package com.bigdata.backstage.modules.dataquality.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WarnPageDto {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "所属项目")
    private Integer projectId;

    @ApiModelProperty(value = "任务名称")
    private Integer taskNameId;

}
