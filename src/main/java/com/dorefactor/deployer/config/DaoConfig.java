package com.dorefactor.deployer.config;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.ApplicationMongoDao;
import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentOrderMongoDao;
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

    @Bean
    public DeploymentOrderDao deploymentOrderDao(MongoOperations mongoOperations) {

        return new DeploymentOrderMongoDao(mongoOperations);
    }
}