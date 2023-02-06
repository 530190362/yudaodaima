package com.bigdata.backstage.modules.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataOverviewVo {
    /**
     * 数据表总数
     */
    private Integer totalTblCount;

    /**
     * 数据占用空间
     */
    private BigDecimal totalTblSize;

    /**
     * 数据总字段数
     */
    private Integer totalTblCol;

    /**
     * 数据总记录数
     */
    private Integer totalTblRow;
}
