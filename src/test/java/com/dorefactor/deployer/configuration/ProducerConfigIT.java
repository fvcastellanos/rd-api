package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.fixture.listener.RabbitListenerFixture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AmqpConfig.class,
        ProducerConfig.class,
        ProducerConfigIT.TestConfig.class
})
public class ProducerConfigIT {

    @Configuration
    static class TestConfig {

        @Bean
        public RabbitListenerFixture rabbitListenerFixture() {

            return new RabbitListenerFixture();
        }
    }
}
