package com.baidu.disconf.web.config;

import com.baidu.disconf.web.config.redis.RedisConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author QingDengYue
 */
@Configuration
@EnableWebMvc
public class DisconfWebConfig extends WebMvcConfigurerAdapter {


    @Autowired
    RedisConfig redisConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
        super.addCorsMappings(registry);
    }


    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        if (StringUtils.isNoneEmpty(redisConfig.getNodes())) {
            String[] nodes = redisConfig.getNodes().split(",");
            for (String node : nodes) {
                if (StringUtils.isNotEmpty(node)) {
                    String nodeAddress = node.replaceAll("\\s", "").trim();
                    clusterConfiguration.addClusterNode(new RedisNode(nodeAddress.split(":")[0], Integer.parseInt(nodeAddress.split(":")[1])));
                }
            }
        }
        clusterConfiguration.setMaxRedirects(Integer.parseInt(redisConfig.getMaxRedirects()));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfiguration);
        return jedisConnectionFactory;
    }


}
