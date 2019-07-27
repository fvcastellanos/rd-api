package com.dorefactor.deployer.dao.configuration;

import com.dorefactor.deployer.configuration.DaoConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    DaoConfig.class
})
public class BaseDaoITConfig {
}