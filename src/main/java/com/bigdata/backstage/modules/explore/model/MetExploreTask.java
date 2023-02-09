package com.bigdata.backstage.modules.explore.model;

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
 * 数据勘探-数据勘探操作表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("met_explore_task")
@ApiModel(value = "MetExploreTask对象", description = "数据勘探-数据勘探操作表")
public class MetExploreTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("勘探的表名")
    private String tblName;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    private Boolean isDeleted;


}
