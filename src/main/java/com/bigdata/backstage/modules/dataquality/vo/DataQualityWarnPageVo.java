package com.bigdata.backstage.modules.dataquality.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据质量监测预警分页查询展示", description = "数据质量监测预警分页查询展示")
public class DataQualityWarnPageVo {
    private Long id;

    @ApiModelProperty("数仓id")
    private String projectName;

    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("任务名称")
    private Integer taskId;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("规则类型")
    private Integer ruleType;

    @ApiModelProperty("规则类型")
    private String ruleTypeName;

    @ApiModelProperty("目标范围")
    private String targetScope;

    @ApiModelProperty("实际值")
    private String actualValue;

    @ApiModelProperty("预警时间")
    private Date warnTime;

}
