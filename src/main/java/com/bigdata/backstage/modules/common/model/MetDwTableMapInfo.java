package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhj666
 * @description: MetDwTableMapInfo ORM
 * @date 2023/6/1 10:53
 */
@Getter
@Setter
@TableName("met_dw_table_map_info")
@ApiModel(value = "MetDwTableMapInfo对象", description = "数仓表映射表")
public class MetDwTableMapInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("数仓ID")
    private String dwId;

    @ApiModelProperty("映射元数据明细表表名")
    private String mapMetaDetailOutline;

    @ApiModelProperty("映射元数据血缘表表名")
    private String mapMetaBloodRelationDetail;

    @ApiModelProperty("映射元数据质量表表名")
    private String mapMetaQuality;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;
}
