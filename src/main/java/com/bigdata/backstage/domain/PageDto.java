package com.bigdata.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "PageDto传输对象", description = "分页对象")
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
