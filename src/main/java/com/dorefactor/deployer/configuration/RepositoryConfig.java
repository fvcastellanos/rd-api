package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.repository.ApplicationRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class RepositoryConfig {

    @Bean
    public ApplicationRepository applicationRepository(MongoOperations mongoOperations) {

        return new ApplicationRepository(mongoOperations);
    }
}