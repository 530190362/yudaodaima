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
 * 数据资产标签表
 * </p>
 *
 * @author macro
 * @since 2023-02-07
 */
@Getter
@Setter
@TableName("met_data_label")
@ApiModel(value = "MetDataLabel对象", description = "数据资产标签表")
public class MetDataLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标签名称")
    private String labelName;

    @ApiModelProperty("标签备注")
    private String labelComment;

    @ApiModelProperty("关联表名")
    private String tableName;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("修改用户")
    private String updateUser;

    @ApiModelProperty("是否删除  0:未删除 1:已删除")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
