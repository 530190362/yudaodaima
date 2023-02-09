package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 质检规则任务映射表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("met_quality_rule_task_relation")
@ApiModel(value = "MetQualityRuleTaskRelation对象", description = "质检规则任务映射表")
public class MetQualityRuleTaskRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("规则id")
    private Integer ruleId;

    @ApiModelProperty("任务id")
    private Integer taskId;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("修改用户")
    private String updateUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除  0:未删除 1:已删除")
    private Integer isDelete;


}
