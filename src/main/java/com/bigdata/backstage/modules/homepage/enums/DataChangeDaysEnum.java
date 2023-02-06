package com.bigdata.backstage.modules.homepage.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 资产变化时间   0:7天  1:15天
 */
@Getter
@AllArgsConstructor
public enum DataChangeDaysEnum {
    /**
     * 0:7天
     */
    SEVEN_DAYS(0, "7"),
    /**
     * 1:15天
     */
    FIFTEEN_DAYS(1, "15");

    private Integer key;

    private String value;

    public static String getValue(Integer key) {
        for (DataChangeDaysEnum value : values()) {
            if (Objects.equals(value.getKey(), key)) {
                return value.getValue();
            }
        }
        return null;
    }
}

