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
@ApiModel(value = "数据质量质检任务分页查询展示", description = "数据质量质检任务分页查询展示")
public class DataQualityTaskPageVo {

    private Long id;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务描述")
    private String taskComment;

    @ApiModelProperty("监测次数")
    private Integer monitorNum;

    @ApiModelProperty("预警次数")
    private Integer warnNum;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("状态 1-上线 0-下线")
    private Integer status;
}
