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
 * 数据标准-表命名规范
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("met_norm_table")
@ApiModel(value = "MetNormTable对象", description = "数据标准-表命名规范")
public class MetNormTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("小层层级名称")
    private String level;

    @ApiModelProperty("命名标准版")
    private String rule;

    @ApiModelProperty("生命周期")
    private String lifecycle;

    @ApiModelProperty("描述")
    private String tableDesc;

    @ApiModelProperty("示例中文名称")
    private String exampleNameZh;

    @ApiModelProperty("示例英文名称")
    private String exampleNameEn;

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
