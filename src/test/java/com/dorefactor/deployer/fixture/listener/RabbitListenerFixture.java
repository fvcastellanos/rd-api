package com.dorefactor.deployer.fixture.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitListenerFixture {

    private String message;

    public RabbitListenerFixture() {
        this.message = "N/A";
    }

    @RabbitListener(queues = "${rd.api.rabbitmq.routing.key}")
    public void receiveMessage(String message) {

        this.message = message;
    }

    public String getLastMessage() {

        return message;
    }

}