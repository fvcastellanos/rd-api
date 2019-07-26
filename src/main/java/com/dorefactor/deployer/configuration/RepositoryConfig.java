package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.repository.ApplicationRepository;
import com.mongodb.client.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class RepositoryConfig {

    // @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        
        return new MongoTemplate(mongoClient, "blah");
    }

    public ApplicationRepository applicationRepository(MongoOperations mongoOperations) {

        return new ApplicationRepository(mongoOperations);
    }
}