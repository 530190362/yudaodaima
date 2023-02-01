package com.bigdata.backstage.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * ##################################
* Description: 动态权限相关业务类
* author: zhj
* date: 2023/1/26 13:23
 * ##################################
*/

public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
