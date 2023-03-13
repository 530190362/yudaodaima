package com.bigdata.backstage.modules.homepage.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 资产变化类型   0:数据记录条数  1:占用空间
 */
@Getter
@AllArgsConstructor
public enum DataChangeTypeEnum {
    /**
     * 0:数据记录条数
     */
    TOTAL_COUNT(0, "数据记录条数"),
    /**
     * 1:数据占用空间
     */
    TOTAL_SIZE(1, "数据占用空间");

    private Integer key;

    private String value;

    public static String getValue(Integer key) {
        for (DataChangeTypeEnum value : values()) {
            if (Objects.equals(value.getKey(), key)) {
                return value.getValue();
            }
        }
        return null;
    }
}

