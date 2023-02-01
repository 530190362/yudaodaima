package com.bigdata.backstage.config;

import com.bigdata.backstage.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

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

}
