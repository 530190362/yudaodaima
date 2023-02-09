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
@ApiModel(value = "数据质量质检规则分页查询展示", description = "数据质量质检规则分页查询展示")
public class DataQualityRulePageVo {

    private Long id;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("规则名称")
    private String ruleName;

    @ApiModelProperty("规则类型")
    private String ruleType;

    @ApiModelProperty("绑定任务数")
    private Integer ruleBindNum;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}
