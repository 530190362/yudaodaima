package com.bigdata.backstage.modules.norm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageDto {

    @ApiModelProperty("自增主键")
    private Long id;

    @ApiModelProperty(value = "页码",required = true)
    @NotNull(message = "页码为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量",required = true)
    @NotNull(message = "每页数量为空")
    private Integer pageSize;
}
