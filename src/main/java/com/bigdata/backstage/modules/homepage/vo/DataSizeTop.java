package com.bigdata.backstage.modules.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSizeTop {
    /**
     * 表名称
     */
    private String tblName;

    /**
     * 数据占用空间
     */
    private BigDecimal tblSize;

    /**
     * 表描述
     */
    private String tblComment;

}

