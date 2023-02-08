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
 * 数仓主表
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
@Getter
@Setter
@TableName("met_dw_info")
@ApiModel(value = "MetDwInfo对象", description = "数仓主表")
public class MetDwInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("数仓名称(英文)")
    private String dwNameEn;

    @ApiModelProperty("数仓名称(中文)")
    private String dwNameZn;

    @ApiModelProperty("数仓描述")
    private String dwDesc;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    private Boolean isDeleted;


}
