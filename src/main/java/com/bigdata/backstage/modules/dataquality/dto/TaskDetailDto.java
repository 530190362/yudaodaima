package com.bigdata.backstage.modules.dataquality.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDetailDto {

    private Long id;

    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务描述")
    private String taskComment;

    @ApiModelProperty("监测次数")
    private Integer monitorNum;

    @ApiModelProperty("预警次数")
    private Integer warnNum;

    @ApiModelProperty("绑定规则")
    private Integer ruleId;

    @ApiModelProperty("绑定表")
    private Integer bindTbl;

    @ApiModelProperty("绑定字段")
    private String bindCol;

    @ApiModelProperty("目标范围起")
    private String targetBegin;

    @ApiModelProperty("目标范围始")
    private String targetEnd;

    @ApiModelProperty("监测频率")
    private Integer monitorFreq;

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

    @ApiModelProperty("是否删除 1-是 0-否")
    private Boolean isDelete;

}
