package com.baidu.disconf.web.config.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    String nodes;

    @Value("${spring.redis.cluster.max-redirects}")
    String maxRedirects;
    @Value("${spring.redis.cluster.timeout}")
    String timeout;
}
