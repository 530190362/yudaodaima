package com.bigdata.backstage.modules.explore.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("view_explore_data_column")
@ApiModel(value = "ViewExportDataColumn对象", description = "VIEW")
public class ViewExploreDataColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("数仓ID")
    private Integer dwId;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("字段名")
    private String colName;

    @ApiModelProperty("字段类型")
    private String colType;

    @ApiModelProperty("字段描述")
    private String colComment;

    @ApiModelProperty("最大长度")
    private String maxLen;

    @ApiModelProperty("最小长度")
    private String minLen;

    @ApiModelProperty("最大值")
    private String maxValue;

    @ApiModelProperty("最小值")
    private String minValue;

    @ApiModelProperty("空值数")
    private Long nullCount;

    @ApiModelProperty("空值率")
    private String nullRate;

    @ApiModelProperty("值种类(所占百分比,超过15个不显示),JSON格式化")
    private String valueKindJson;

    @ApiModelProperty("是否只有一个值")
    private Boolean isOnlyValue;


}
