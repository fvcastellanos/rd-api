package com.dorefactor.deployer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AmqpConfig.class,
        ProducerConfig.class
})
public class ProducerConfigIT {
}
