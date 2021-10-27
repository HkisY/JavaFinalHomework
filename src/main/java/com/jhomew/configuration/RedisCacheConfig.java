package com.jhomew.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 * RedisTemplate 序列化配置
 *
 * @author Hxin
 * @version 1.0
 * @since 2021/10/15 9:45 下午
 */

@Configuration
public class RedisCacheConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Long> redisTemplate() {
        final RedisTemplate<String, Long> template = new RedisTemplate<String, Long>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        template.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        return template;
    }

}
