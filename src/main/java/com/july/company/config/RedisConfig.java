package com.july.company.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis 配置参数
 * 开启redis缓存设置，默认应该是关闭的.
 * @author zengxueqi
 * @since 2019/07/22 9:24
 */
@Configuration
@ConditionalOnProperty(prefix = "cache", name = "type", havingValue = "redis", matchIfMissing = false)
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<?> fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer((RedisSerializer<String>) fastJsonRedisSerializer));
        RedisCacheConfiguration redisCacheConfiguration2 = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer((RedisSerializer<String>) fastJsonRedisSerializer))
                .entryTtl(Duration.ofMinutes(10));
        Set<String> caches = new HashSet<>();
        caches.add("cache-redis-1");
        caches.add("cache-redis-2");


        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("cache-redis-1", redisCacheConfiguration);
        cacheConfigs.put("cache-redis-2", redisCacheConfiguration2);

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .initialCacheNames(caches)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<?> fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        ParserConfig.getGlobalInstance().addAccept("com.july.");

        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 重新默认定义key生成规则， 包名+方法名+参数列表
     * @return key
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("::").append(method.getName()).append(":");
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };

    }

    @Bean
    public ValueOperations<?, ?> getValueOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public SetOperations<?, ?> getSetOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ListOperations<?, ?> getListOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public HashOperations<?, ?, ?> getHashOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ZSetOperations<?, ?> getZSetOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public GeoOperations<?, ?> getGeoOperationsBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForGeo();
    }

    @Bean
    public HyperLogLogOperations<?, ?> getOpsForHyperLogLogBean(RedisTemplate<?, ?> redisTemplate) {
        return redisTemplate.opsForHyperLogLog();
    }

}
