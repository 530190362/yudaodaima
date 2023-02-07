package com.bigdata.backstage.modules.norm.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NormTableVo {


    @ExcelProperty(value = "ID", index = 0)
    private Long id;

    @ExcelProperty(value = "小层层级名称", index = 1)
    private String level;

    @ExcelProperty(value = "命名标准版", index = 2)
    private String rule;


    @ExcelProperty(value = "生命周期", index = 3)
    private String lifecycle;

    @ExcelProperty(value = "描述", index = 4)
    private String tableDesc;

    @ExcelProperty(value = "示例中文名称", index = 5)
    private String exampleNameZh;

    @ExcelProperty(value = "示例英文名称", index = 6)
    private String exampleNameEn;

    @ExcelProperty(value = "创建人", index = 7)
    private String createUser;

    @ExcelProperty(value = "更新人", index = 8)
    private String updateUser;

    @ExcelProperty(value = "创建时间", index = 9)
    private Date createTime;

    @ExcelProperty(value = "更新时间", index = 10)
    private Date updateTime;

    @ExcelProperty(value = "是否删除", index = 11)
    private Boolean isDeleted;
}
