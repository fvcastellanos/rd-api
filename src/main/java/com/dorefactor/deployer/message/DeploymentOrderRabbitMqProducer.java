package com.dorefactor.deployer.message;

import org.springframework.amqp.rabbit.core.RabbitOperations;

public class DeploymentOrderRabbitMqProducer implements DeploymentOrderProducer {

    private final String exchange;
    private final String routingKey;

    private final RabbitOperations rabbitOperations;

    public DeploymentOrderRabbitMqProducer(String exchange, String routingKey,RabbitOperations rabbitOperations) {

        this.exchange = exchange;
        this.routingKey = routingKey;
        this.rabbitOperations = rabbitOperations;
    }

    @Override
    public void produce(String message) {

        rabbitOperations.s
    }
}
