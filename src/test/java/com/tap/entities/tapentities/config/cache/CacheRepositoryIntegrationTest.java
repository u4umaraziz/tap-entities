package com.tap.entities.tapentities.config.cache;

import com.tap.entities.tapentities.TapEntitiesApplication;
import com.tap.entities.tapentities.util.TestRedis;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import static com.tap.entities.tapentities.config.cache.CacheNames.ENTITY_IDEMPOTENT_CACHE;
import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = TapEntitiesApplication.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {TestRedis.class})
class CacheRepositoryIntegrationTest {

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    CacheConfig cacheConfig;

    @SneakyThrows
    @Test
    void cacheEvictionShouldWork() {

        String uuid = UUID.randomUUID().toString();
        String value = "value";
        cacheRepository.saveToCache(ENTITY_IDEMPOTENT_CACHE, uuid, value);
        // object in cache
        Optional<Object> stored = getFromCache(uuid);
        Assertions.assertTrue(stored.isPresent());

        //object auto evicted
        await()
                .atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofMillis(100))
                .until(() -> getFromCache(uuid).isEmpty());
    }

    private Optional<Object> getFromCache(String uuid) {
        return cacheRepository.findInCache(ENTITY_IDEMPOTENT_CACHE, uuid);
    }

}