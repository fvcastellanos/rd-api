package com.dorefactor.deployer.repository.configuration;

import com.dorefactor.deployer.configuration.RepositoryConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    RepositoryConfig.class
})
public class BaseRepositoryITConfig {
}