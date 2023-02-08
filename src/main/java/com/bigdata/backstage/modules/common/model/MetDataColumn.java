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
 * <p>
 * 数据资产-ODPS表字段
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Getter
@Setter
@TableName("met_data_column")
@ApiModel(value = "MetDataColumn对象", description = "数据资产-ODPS表字段")
public class MetDataColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("数仓ID")
    private Integer dwId;

    @ApiModelProperty("表名")
    private String tblName;

    @ApiModelProperty("字段名")
    private String colName;

    @ApiModelProperty("字段类型")
    private String colType;

    @ApiModelProperty("字段备注")
    private String colComment;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;


}
