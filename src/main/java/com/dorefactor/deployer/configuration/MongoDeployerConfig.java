package com.dorefactor.deployer.configuration;

import com.mongodb.client.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDeployerConfig {
    
    @Bean
    public MongoOperations mongoOperations(MongoClient mongoClient) {
        
        return new MongoTemplate(mongoClient, "blah");
    }
}