//package com.jhomew.configuration;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Redis 缓存配置及服务
// *
// * @author Hxin
// * @version 1.0
// * @since 2021/10/15 9:45 下午
// */
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfig {
//    /**
//     * 以【类名全限定名】为缓存的key值
//     * @return KeyGenerator
//     */
//    @Bean
//    public KeyGenerator classKey() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            return sb.toString();
//        };
//    }
//
//    /**
//     * 以【类名+方法名】为缓存的key值
//     * @return KeyGenerator接口内方法 Object generate(Object target, Method method, Object... params);
//     */
//    @Bean
//    public KeyGenerator classMethodKey() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(".");
//            sb.append(method.getName());
//            return sb.toString();
//        };
//    }
//
//
//    /**
//     * 以【类名+方法名+参数】为缓存的key值
//     * @return KeyGenerator
//     */
//    @Bean
//    public KeyGenerator classMethodParamsKey() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(".");
//            sb.append(method.getName());
//            for (Object obj : params) {
//                sb.append("#");
//                sb.append(obj == null ? "" : obj.toString());
//            }
//            return sb.toString();
//        };
//    }
//
//    /**
//     * 设置cache缓存过期时间
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//                this.getRedisCacheConfigurationWithTtl(DEFAULT_EXIRE_TIME), // 默认策略，未配置的 key 会使用这个
//                this.getRedisCacheConfigurationMap() // 指定 key 策略
//        );
//    }
//    /**
//     * 给指定类型的key设置过期时间
//     * @return
//     */
//    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//        //指定cache头名称为sysUser的方法缓存过期时间为100s
//        redisCacheConfigurationMap.put("sysUser", this.getRedisCacheConfigurationWithTtl(100));
//        return redisCacheConfigurationMap;
//    }
//
//    /**
//     * 设置默认过期时间
//     * @param seconds
//     * @return
//     */
//    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // LocalDateTime时间类型存入redis中反序列化（spring cache）
//        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        om.registerModule(new JavaTimeModule());
//
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
//                RedisSerializationContext
//                        .SerializationPair
//                        .fromSerializer(jackson2JsonRedisSerializer)
//        ).entryTtl(Duration.ofSeconds(seconds));
//
//        return redisCacheConfiguration;
//    }
//}
