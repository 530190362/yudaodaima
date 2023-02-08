package com.bigdata.backstage.modules.res.dto;

import com.bigdata.backstage.domain.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResFunctionDto  extends PageDto {

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

    @ApiModelProperty("函数来源 1-公司创建的函数 0-系统自带函数")
    private Boolean isCompany;

}
