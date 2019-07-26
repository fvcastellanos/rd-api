package com.dorefactor.deployer.configuration;

import com.mongodb.client.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class RepositoryConfig {

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        
        return new MongoTemplate(mongoClient, "blah");
    }
}