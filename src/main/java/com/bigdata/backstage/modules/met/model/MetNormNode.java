package com.bigdata.backstage.modules.met.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据标准-任务节点
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("met_norm_node")
@ApiModel(value = "MetNormNode对象", description = "数据标准-任务节点")
public class MetNormNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("节点类型/名称")
    private String name;

    @ApiModelProperty("节点命名规范")
    private String rule;

    @ApiModelProperty("节点描述")
    private String nodeDesc;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    private Boolean isDeleted;


}
