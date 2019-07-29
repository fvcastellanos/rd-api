package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.ApplicationMongoDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.dao.DeploymentTemplateMongoDao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class DaoConfig {

    @Bean
    public ApplicationDao applicationDao(MongoOperations mongoOperations) {

        return new ApplicationMongoDao(mongoOperations);
    }

    @Bean
    public DeploymentTemplateDao deploymentTemplateDao(MongoOperations mongoOperations) {

        return new DeploymentTemplateMongoDao(mongoOperations);
    }
}