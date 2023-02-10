package com.bigdata.backstage.modules.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 数据资产-ODPS表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-08
 */
@Getter
@Setter
@TableName("met_data_table")
@ApiModel(value = "MetDataTable对象", description = "数据资产-ODPS表")
public class MetDataTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("数仓名称")
    @TableField(exist = false)
    private String dwName;

    @ApiModelProperty("ODS层数据源类型")
    @TableField(exist = false)
    private String SourceType;

    @ApiModelProperty("数仓ID")
    private Integer dwId;

    @ApiModelProperty("表名")
    private String tblName;

    @ApiModelProperty("表备注")
    private String tblComment;

    @ApiModelProperty("表创建日期")
    private Date tblCreateTime;

    @ApiModelProperty("表更新日期")
    private Date tblUpdateTime;

    @ApiModelProperty("是否是分区表 1-是 0-否")
    private Boolean isPt;

    @ApiModelProperty("最新分区")
    private String ptLast;

    @ApiModelProperty("表生命周期")
    private Integer tblLifecycle;

    @ApiModelProperty("表类型 内部表/外部表")
    private String tblType;

    @ApiModelProperty("表大小 单位:MB")
    private BigDecimal tblSize;

    @ApiModelProperty("字段数")
    private Integer tblColNum;

    @ApiModelProperty("总行数")
    private Integer allRowCount;

    @ApiModelProperty("最新分区行数")
    private Integer rowCount;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;


}
