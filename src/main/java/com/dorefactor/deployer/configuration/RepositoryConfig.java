package com.dorefactor.deployer.configuration;

import com.mongodb.reactivestreams.client.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class RepositoryConfig {

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        
        return new ReactiveMongoTemplate(mongoClient, "blah");
    }
}