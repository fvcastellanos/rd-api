package com.dorefactor.deployer.repository.configuration;

import java.io.IOException;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;

// @Configuration
public class MongoDbConfig {

    // @Bean(destroyMethod = "close")
    // public MongoClient mongoClient() throws IOException {

    //     return new EmbeddedMongoBuilder()
    //         .version(Version.V4_0_2)
    //         .bindIp("127.0.0.1")
    //         .port(27018)
    //         .build();

        
    // }
}