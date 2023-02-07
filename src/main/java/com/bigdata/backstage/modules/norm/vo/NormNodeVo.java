package com.bigdata.backstage.modules.norm.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NormNodeVo {

    @ExcelProperty(value = "ID", index = 0)
    private Long id;

    @ExcelProperty(value = "节点类型/名称", index = 1)
    private String name;

    @ExcelProperty(value = "节点命名规范", index = 2)
    private String rule;

    @ExcelProperty(value = "节点描述", index = 3)
    private String nodeDesc;

    @ExcelProperty(value = "创建人", index = 4)
    private String createUser;

    @ExcelProperty(value = "更新人", index = 5)
    private String updateUser;

    @ExcelProperty(value = "创建时间", index = 6)
    private Date createTime;

    @ExcelProperty(value = "更新时间", index = 7)
    private Date updateTime;

    @ExcelProperty(value = "是否删除", index = 8)
    private Boolean isDeleted;
}
