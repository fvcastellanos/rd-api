package com.dorefactor.deployer.fixture.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitListenerFixture {

    private static final Logger logger = LoggerFactory.getLogger(RabbitListenerFixture.class);

    private static String message = "N/A";

    @RabbitListener(queues = "com.dorefactor.deploy.command")
    public void receiveMessage(String message) {

        logger.info("message received: {}", message);
        RabbitListenerFixture.message = message;
    }

    public static String getLastMessage() {

        logger.info("last message: {}", message);
        return message;
    }

}