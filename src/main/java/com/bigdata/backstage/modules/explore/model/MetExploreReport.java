package com.bigdata.backstage.modules.explore.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 数据勘探-数据勘探报告表
 * </p>
 *
 * @author bigdata
 * @since 2023-02-10
 */
@Getter
@Setter
@TableName("met_explore_report")
@ApiModel(value = "MetExploreReport对象", description = "数据勘探-数据勘探报告表")
public class MetExploreReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("数仓ID")
    private Integer dwId;

    @ApiModelProperty("数仓名称")
    @TableField(exist = false)
    private String dwName;

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
    private Long distinctCount;

    @ApiModelProperty("总记录数(最近的分区)")
    private Long totalCount;

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

    @ApiModelProperty("读取记录数花费的时间单位:秒")
    private Integer spendTime;

    @ApiModelProperty("探查时间")
    private Date etlTm;

    @ApiModelProperty("日期")
    private String dt;

    @ApiModelProperty("创建时间")
    private Date createTime;


}
