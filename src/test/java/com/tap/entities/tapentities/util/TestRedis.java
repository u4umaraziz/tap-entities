package com.tap.entities.tapentities.util;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class TestRedis implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:6.2-alpine")).withExposedPorts(6379);

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        redis.start();
        TestPropertyValues.of(
                "spring.redis.host=" + redis.getHost(),
                "spring.redis.port=" + redis.getFirstMappedPort()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
