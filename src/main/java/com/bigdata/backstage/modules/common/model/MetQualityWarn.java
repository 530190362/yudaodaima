package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据质量-监测预警
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
@Getter
@Setter
@TableName("met_quality_warn")
@ApiModel(value = "MetQualityWarn对象", description = "数据质量-监测预警")
public class MetQualityWarn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("数仓id")
    private Integer dwId;

    @ApiModelProperty("任务名称")
    private Integer taskId;

    @ApiModelProperty("规则类型")
    private Integer ruleType;

    @ApiModelProperty("目标范围")
    private String targetScope;

    @ApiModelProperty("实际值")
    private String actualValue;

    @ApiModelProperty("预警时间")
    private Date warnTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDelete;


}
