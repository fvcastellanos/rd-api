package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.service.ApplicationService;

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
}