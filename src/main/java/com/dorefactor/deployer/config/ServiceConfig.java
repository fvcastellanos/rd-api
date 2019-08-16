package com.dorefactor.deployer.config;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.dorefactor.deployer.service.ApplicationService;

import com.dorefactor.deployer.service.DeploymentService;
import com.dorefactor.deployer.service.DeploymentTemplateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Gson gson() {

        return new GsonBuilder().create();
    }

    @Bean
    public ApplicationService applicationService(ApplicationDao applicationDao) {

        return new ApplicationService(applicationDao);
    }

    @Bean
    public DeploymentTemplateService deploymentTemplateService(DeploymentTemplateDao deploymentTemplateDao) {

        return new DeploymentTemplateService(deploymentTemplateDao);
    }

    @Bean
    public DeploymentService deploymentService(DeploymentTemplateDao deploymentTemplateDao,
                             DeploymentOrderDao deploymentOrderDao,
                             DeploymentOrderProducer deploymentOrderProducer,
                             Gson gson) {
        return new DeploymentService(deploymentOrderDao, deploymentTemplateDao, deploymentOrderProducer, gson);
    }
}