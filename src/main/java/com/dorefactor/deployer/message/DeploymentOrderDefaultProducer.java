package com.dorefactor.deployer.message;

import org.springframework.amqp.rabbit.core.RabbitOperations;

public class DeploymentOrderDefaultProducer implements DeploymentOrderProducer {

    private final RabbitOperations rabbitOperations;

    public DeploymentOrderDefaultProducer(RabbitOperations rabbitOperations) {

        this.rabbitOperations = rabbitOperations;
    }

    @Override
    public void produce(String message) {

    }
}
