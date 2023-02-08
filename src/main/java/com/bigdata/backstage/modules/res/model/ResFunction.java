package com.bigdata.backstage.modules.res.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资源管理-UDF函数管理
 * </p>
 *
 * @author bigdata
 * @since 2023-02-07
 */
@Getter
@Setter
@TableName("met_res_function")
@ApiModel(value = "ResFunction对象", description = "资源管理-UDF函数管理")
public class ResFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("函数英文名称")
    private String name;

    @ApiModelProperty("函数中文名称")
    private String nameZh;

    @ApiModelProperty("分类 UDF/UDAF/UDTF")
    private String type;

    @ApiModelProperty("类名")
    private String className;

    @ApiModelProperty("资源列表")
    private String jarName;

    @ApiModelProperty("描述")
    private String functionDesc;

    @ApiModelProperty("命令格式")
    private String command;

    @ApiModelProperty("参数说明")
    private String paramDesc;

    @ApiModelProperty("返回值")
    private String returnValue;

    @ApiModelProperty("示例")
    private String example;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("函数来源 1-公司创建的函数 0-系统自带函数")
    private Boolean isCompany;

    @ApiModelProperty("是否删除 1-是 0-否")
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;


}
