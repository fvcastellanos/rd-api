package com.dorefactor.deployer.web.config;

import com.dorefactor.deployer.config.AmqpConfig;
import com.dorefactor.deployer.config.DaoConfig;
import com.dorefactor.deployer.config.ProducerConfig;
import com.dorefactor.deployer.config.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DaoConfig.class,
        AmqpConfig.class,
        ProducerConfig.class,
        ServiceConfig.class
})
public class WebITConfig {
}
