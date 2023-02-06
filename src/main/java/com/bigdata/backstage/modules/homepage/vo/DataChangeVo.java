package com.bigdata.backstage.modules.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataChangeVo {

    /**
     * 数据时间
     */
    private String date;

    /**
     * 数据量
     */
    private BigDecimal dataNum;
}
