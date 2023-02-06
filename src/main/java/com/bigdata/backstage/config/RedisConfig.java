package com.bigdata.backstage.config;

import com.bigdata.backstage.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * ##################################
* Description: Redis配置类
* author: zhj
* date: 2023/1/26 13:21
 * ##################################
*/

@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
    //自定义key规则
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
