package com.bigdata.backstage.modules.explore.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据勘探-数据勘探报告表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("met_explore_report")
@ApiModel(value = "MetExploreReport对象", description = "数据勘探-数据勘探报告表")
public class MetExploreReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表描述")
    private String tableComment;

    @ApiModelProperty("字段名")
    private String colName;

    @ApiModelProperty("字段类型")
    private String colType;

    @ApiModelProperty("字段描述")
    private String colComment;

    @ApiModelProperty("去重记录数")
    private String distinctCount;

    @ApiModelProperty("总记录数(最近的分区)")
    private String totalCount;

    @ApiModelProperty("最大长度")
    private String maxLen;

    @ApiModelProperty("最小长度")
    private String minLen;

    @ApiModelProperty("最大值")
    private String maxValue;

    @ApiModelProperty("最小值")
    private String minValue;

    @ApiModelProperty("空值数")
    private String nullCount;

    @ApiModelProperty("空值率")
    private String nullRate;

    @ApiModelProperty("字段值质量,使用空值率判断:高(<30),中(30,80),低(>80)")
    private String valueQuality;

    @ApiModelProperty("种类(超过10个不显示),JSON格式化")
    private String valueKind;

    @ApiModelProperty("值种类(所占百分比,超过15个不显示),JSON格式化")
    private String valueKindJson;

    @ApiModelProperty("值类型 空值类|唯一类|二分类|多分类|无穷类")
    private String valueStatus;

    @ApiModelProperty("是否有重复值")
    private String isRepeat;

    @ApiModelProperty("是否存在null")
    private String isExistNull;

    @ApiModelProperty("是否全为null")
    private String isAllNull;

    @ApiModelProperty("是否全是相同的值")
    private String isOnlyValue;

    @ApiModelProperty("是否全是不同的值且没有空值")
    private String isDiffValue;

    @ApiModelProperty("读取记录数花费的时间单位:秒")
    private String spendTime;

    @ApiModelProperty("ETL时间")
    private String etlTm;

    @ApiModelProperty("日期")
    private String dt;


}
