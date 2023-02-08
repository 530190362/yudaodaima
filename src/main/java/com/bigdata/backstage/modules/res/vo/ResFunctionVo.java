package com.bigdata.backstage.modules.res.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResFunctionVo {

    @ExcelProperty(value = "id", index = 0)
    private Long id;

    @ExcelProperty(value = "函数英文名称", index = 1)
    private String name;

    @ExcelProperty(value = "函数中文名称", index = 2)
    private String nameZh;

    @ExcelProperty(value = "分类", index = 3)
    private String type;

    @ExcelProperty(value = "类名", index = 4)
    private String className;

    @ExcelProperty(value = "资源列表", index = 5)
    private String jarName;

    @ExcelProperty(value = "描述", index = 6)
    private String functionDesc;

    @ExcelProperty(value = "命令格式", index = 7)
    private String command;

    @ExcelProperty(value = "参数说明", index = 8)
    private String paramDesc;

    @ExcelProperty(value = "返回值", index = 9)
    private String returnValue;

    @ExcelProperty(value = "示例", index = 10)
    private String example;

    @ExcelProperty(value = "创建人", index = 11)
    private String createUser;

    @ExcelProperty(value = "更新人", index = 12)
    private String updateUser;

    @ExcelProperty(value = "创建时间", index = 13)
    private Date createTime;

    @ExcelProperty(value = "更新时间", index = 14)
    private Date updateTime;

    @ExcelProperty(value = "函数来源(1:公司2:系统)", index = 15)
    private Boolean isCompany;

    @ExcelProperty(value = "是否删除", index = 16)
    private Boolean isDeleted;

}
