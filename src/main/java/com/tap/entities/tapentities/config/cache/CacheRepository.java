package com.tap.entities.tapentities.config.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheRepository {


    private final CacheManager cacheManager;

    public <R> Optional<R> findInCache(String cacheName,
                                       Object key,
                                       Class<R> clazz) {
        return findInCache(cacheName, key)
                .filter(clazz::isInstance)
                .map(clazz::cast);
    }

    public Optional<Object> findInCache(String cacheName,
                                        Object key) {
        Cache.ValueWrapper cachedValue = cache(cacheName).get(key);
        return Optional.ofNullable(cachedValue)
                .map(Cache.ValueWrapper::get);
    }

    public void saveToCache(String cacheName,
                            String key,
                            Object value) {
        cache(cacheName).put(key, value);
    }

    private Cache cache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            throw new CacheNotFoundException("Cache " + cacheName + " was not found");
        }
        return cache;
    }
}
