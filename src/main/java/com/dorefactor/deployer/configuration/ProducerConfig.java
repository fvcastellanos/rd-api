package com.dorefactor.deployer.configuration;

import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.dorefactor.deployer.message.DeploymentOrderRabbitMqProducer;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Value("${rd.api.rabbitmq.exchange:com.dorefactor.deploy.command}")
    private String exchange;

    @Value("${rd.api.rabbitmq.queue:regular-deployer-exchange}")
    private String queue;

    @Bean
    public DeploymentOrderProducer deploymentOrderRabbitMqProducer(RabbitOperations rabbitOperations) {

        return new DeploymentOrderRabbitMqProducer(exchange, queue, rabbitOperations);
    }
}
