package com.dorefactor.deployer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    DaoConfig.class
})
public class BaseDaoITConfig {
}