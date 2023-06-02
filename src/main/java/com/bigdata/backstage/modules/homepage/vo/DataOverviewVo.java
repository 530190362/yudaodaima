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
    private Integer totalTbl;

    /**
     * 数据占用空间
     */
    private BigDecimal totalSize;

    /**
     * 数据总字段数
     */
    private Integer colCount;

    /**
     * 数据总记录数
     */
    private Integer rowCount;
}
