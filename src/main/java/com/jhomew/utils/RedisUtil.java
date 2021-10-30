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
     * @param redisTemplate RedisTemplate
     * @param redisKey key
     * @param obj Object
     */
    public static void save(RedisTemplate redisTemplate, String redisKey, Object obj) {
        //TODO 操作检查
        //此处暂不进行检查，操作的时候注意传正确的参数
        redisTemplate.opsForValue().set(redisKey, obj);
    }

    /**
     * 拼接redis key
     * @param pre 前缀
     * @param content 内容
     * @return String
     */
    public static String crtRedisKey(String pre,String content){
        return new StringBuilder().append(pre).append(content).toString();
    }

    /**
     * 从redis缓存取数据
     * @param redisTemplate RedisTemplate<String, Long>
     * @param id  id
     * @return
     */
    public static Object get(RedisTemplate<String, Long> redisTemplate, String id) {
        return redisTemplate.opsForValue().get(id);
    }

    /**
     * 从redis缓存删除数据
     * @param redisTemplate RedisTemplate<String, Long>
     * @param redisKey id
     */
    public static void delete(RedisTemplate<String, Long> redisTemplate, String redisKey) {
        redisTemplate.delete(redisKey);
    }
}
