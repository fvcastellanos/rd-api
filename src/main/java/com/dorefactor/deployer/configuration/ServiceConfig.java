package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.service.ApplicationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ApplicationService applicationService(ApplicationDao applicationDao) {

        return new ApplicationService(applicationDao);
    }
}