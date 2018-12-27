package com.hong.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * <br>Sprig Boot Redis配置类</br>
 *  继承CachingConfigurerSupport并重写方法，配合@EnableCaching注解实现spring缓存框架的使用
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisTemplate.class)
public class RedisAutoConfiguration {

    //全局的默认缓存时间 5min
    public static final long DEFALUT_CACHE_TIME = 5*60*1000;

   /* @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        *//**
         * spring-cache可以搭配的缓存很多,也就有很多org.springframework.cache.CacheManager使用
         * 这里用的是Redis,所以是RedisCacheManager
         *//*
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        *//**
         *  设置默认的缓存过期时间
         *  因为 @Cacheable 注解本身不支持配置过期时间,这里设置一个全局的过期时间
         *//*
        rcm.setDefaultExpiration(DEFALUT_CACHE_TIME);//秒
        // 也可以在Map中给不同的key设置对应的过期时间
        *//*Map<String,Long> keyExpireMap = new HashMap<>();
        keyExpireMap.put("key",1000L);
        rcm.setExpires(keyExpireMap);*//*
        return rcm;
    }*/

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        log.info("【spring boot component】 【redis】 init successful!");
        return template;
    }

}