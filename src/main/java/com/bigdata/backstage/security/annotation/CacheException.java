package com.bigdata.backstage.security.annotation;

import java.lang.annotation.*;

/**
 * ##################################
* Description: 自定义注解，有该注解的缓存方法会抛出异常
* author: zhj
* date: 2023/1/26 13:22
 * ##################################
*/

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
