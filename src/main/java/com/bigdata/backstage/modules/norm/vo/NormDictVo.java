package com.bigdata.backstage.modules.norm.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class NormDictVo {


    @ExcelProperty(value = "id", index = 0)
    private Long id;

    @ExcelProperty(value = "上级字典名称id", index = 1)
    private Long parentId;

    @ExcelProperty(value = "字典编码", index = 2)
    private String nameCode;

    @ExcelProperty(value = "字典名称", index = 3)
    private String name;

    @ExcelProperty(value = "字典描述", index = 4)
    private String dictDesc;

    @ExcelProperty(value = "创建人", index = 5)
    private String createUser;

    @ExcelProperty(value = "更新人", index = 6)
    private String updateUser;

}
