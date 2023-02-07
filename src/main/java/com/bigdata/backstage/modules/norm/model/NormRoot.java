package com.bigdata.backstage.modules.norm.model;

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
 * 数据标准-字根库
 * </p>
 *
 * @author macro
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("met_norm_root")
@ApiModel(value = "MetNormRoot对象", description = "数据标准-字根库")
public class NormRoot implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("词根中文")
    private String nameZh;

    @ApiModelProperty("词根描述")
    private String rootDesc;

    @ApiModelProperty("词根英文")
    private String nameEn;

    @ApiModelProperty("词根英文简写")
    private String nameShortEn;

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
