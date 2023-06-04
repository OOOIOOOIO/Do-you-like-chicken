//package com.sh.chicken.global.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//
////@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        return new LettuceConnectionFactory(host, port); // Redis Client는 Lettuce 사용
//    }
//
//    // RedisTemplate 사용
//    // 트랜잭션을 적용하고 싶을 때
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate(){
//        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
//    @SuppressWarnings("deprecation")
//    @Bean
//    public CacheManager cacheManager() {
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());
//
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig() //
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) // Value Serializer 변경, 캐싱할 API의 응답값 데이터 타입 : JSON / String과 직렬화되지 않은 데이터는 이렇게 따로 설정
//                .prefixKeysWith("Test:") // Key Prefix로 "Test:"를 앞에 붙여 저장
//                .entryTtl(Duration.ofMinutes(30)); // 캐시 수명 30분
//
//        builder.cacheDefaults(configuration);
//
//        return builder.build();
//    }
//}
//
