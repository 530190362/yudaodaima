package com.bigdata.backstage.modules.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCountTop {
    /**
     * 表名称
     */
    private String tblName;

    /**
     * 数据记录数
     */
    private double tblCount;

    /**
     * 表描述
     */
    private String tblComment;

}

