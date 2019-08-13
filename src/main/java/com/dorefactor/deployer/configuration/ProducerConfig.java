package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.dorefactor.deployer.message.DeploymentOrderRabbitMqProducer;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Value("${rd.api.rabbitmq.exchange}")
    private String exchange;

    @Value("${rd.api.rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public DeploymentOrderProducer deploymentOrderRabbitMqProducer(RabbitOperations rabbitOperations) {

        return new DeploymentOrderRabbitMqProducer(exchange, routingKey, rabbitOperations);
    }
}
