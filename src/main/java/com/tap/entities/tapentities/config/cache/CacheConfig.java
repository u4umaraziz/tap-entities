package com.tap.entities.tapentities.config.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

import static com.tap.entities.tapentities.config.cache.CacheNames.ENTITY_IDEMPOTENT_CACHE;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@EnableCaching
@Slf4j
@Configuration
@RequiredArgsConstructor
class CacheConfig implements CachingConfigurer {

    private final JedisConnectionFactory jedisConnectionFactory;

    @Bean
    @Override
    public CacheManager cacheManager() {
        return RedisCacheManager
                .RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory)
                .withCacheConfiguration(ENTITY_IDEMPOTENT_CACHE, withExpiry(ofSeconds(3)))
                .build();
    }

    private RedisCacheConfiguration withExpiry(Duration ttl) {
        return defaultCacheConfig().entryTtl(ttl);
    }

}
