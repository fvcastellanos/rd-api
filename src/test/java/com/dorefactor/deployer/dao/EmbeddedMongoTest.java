package com.dorefactor.deployer.dao;

import java.io.IOException;

import com.mongodb.Mongo;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;

@ContextConfiguration(classes = EmbeddedMongoTest.TestConfig.class)
public class EmbeddedMongoTest {

    @Test
    public void startMongoTest() {

    }

    @Configuration
    protected static class TestConfig {

        @Bean(destroyMethod = "close")
        public Mongo mongo() throws IOException {

            return new EmbeddedMongoBuilder()
                .version(Version.V4_0_2)
                .bindIp("127.0.0.1")
                .port(27018)
                .build();
        }

    }

}