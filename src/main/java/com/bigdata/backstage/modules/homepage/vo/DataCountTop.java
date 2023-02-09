package com.bigdata.backstage.modules.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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

    /**
     * 创建时间
     */
    private Date createTime;

}

