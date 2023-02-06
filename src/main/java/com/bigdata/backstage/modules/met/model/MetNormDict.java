package com.bigdata.backstage.modules.met.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据标准-字典库
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("met_norm_dict")
@ApiModel(value = "MetNormDict对象", description = "数据标准-字典库")
public class MetNormDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("字典编码")
    private String nameCode;

    @ApiModelProperty("字典名称")
    private String name;

    @ApiModelProperty("上级字典名称id")
    private Long parentId;

    @ApiModelProperty("字典描述")
    private String dictDesc;

    @ApiModelProperty("树结构")
    private String treePath;

    @ApiModelProperty("排序")
    private Integer sortValue;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("状态（1正常 0停用）")
    private Boolean status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;

    @ApiModelProperty("是否包含子节点")
    @TableField(exist = false)
    private Boolean hasChildren;

}
