package com.dorefactor.deployer.web.config;

import com.dorefactor.deployer.config.AmqpConfig;
import com.dorefactor.deployer.config.DaoConfig;
import com.dorefactor.deployer.config.ProducerConfig;
import com.dorefactor.deployer.config.ServiceConfig;
import com.dorefactor.deployer.web.controller.AbstractController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MongoDbWebITConfig.class,
        DaoConfig.class,
        AmqpConfig.class,
        ProducerConfig.class,
        ServiceConfig.class
})
@ComponentScan(basePackageClasses = AbstractController.class)
public class WebITConfig {
}
