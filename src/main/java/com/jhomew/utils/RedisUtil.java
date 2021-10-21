package com.jhomew.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/15 12:42 下午
 */
@Component
public class RedisUtil {
    /**
     * 向redis中存储数据
     *
     * @param redisTemplate
     * @param redisKey
     * @param obj
     */
    public static void save(RedisTemplate redisTemplate, String redisKey, Object obj) {
        //TODO 操作检查
        //此处暂不进行检查，操作的时候注意传正确的参数
        redisTemplate.opsForValue().set(redisKey, obj);
    }
}