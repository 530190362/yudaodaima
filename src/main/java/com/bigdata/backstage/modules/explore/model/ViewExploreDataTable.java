package com.bigdata.backstage.modules.explore.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
@Getter
@Setter
@TableName("view_explore_data_table")
@ApiModel(value = "ViewExportDataTable对象", description = "VIEW")
public class ViewExploreDataTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("数仓ID")
    private Integer dwId;

    @ApiModelProperty("数仓名称")
    @TableField(exist = false)
    private String dwName;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表描述")
    private String tableComment;

    private BigDecimal distinctCount;

    private BigDecimal totalCount;

    @ApiModelProperty("读取记录数花费的时间单位:秒")
    private Integer spendTime;

    @ApiModelProperty("探查时间")
    private String etlTm;


}
