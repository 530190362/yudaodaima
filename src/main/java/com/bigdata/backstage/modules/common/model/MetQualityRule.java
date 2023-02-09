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
 * 数据质量-质检规则
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("met_quality_rule")
@ApiModel(value = "MetQualityRule对象", description = "数据质量-质检规则")
public class MetQualityRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("规则名称")
    private String ruleName;

    @ApiModelProperty("规则描述")
    private String ruleComment;

    @ApiModelProperty("规则类型")
    private Integer ruleType;

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

    @ApiModelProperty("是否删除 1-是 0-否")
    private Boolean isDelete;


}
