package com.dorefactor.deployer.config;

import com.mongodb.client.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbConfig {

    @Value("${spring.data.mongodb.database}")
    private String dataBaseName;

    @Bean
    public MongoOperations mongoOperations(MongoClient mongoClient) {
        
        return new MongoTemplate(mongoClient, dataBaseName);
    }
}