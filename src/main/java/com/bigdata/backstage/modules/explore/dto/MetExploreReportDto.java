package com.bigdata.backstage.modules.explore.dto;


import com.bigdata.backstage.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据勘探-勘探报告", description = "MetExploreReportDto传输对象")
public class MetExploreReportDto extends PageDto {

    @ApiModelProperty("数仓id")
    private Long dwId;

    @ApiModelProperty("数仓名称")
    private String dwName;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表描述")
    private String tableComment;

}
