package com.dorefactor.deployer.fixture.listener;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitListenerFixture {

    private static final Logger logger = LoggerFactory.getLogger(RabbitListenerFixture.class);

    private static String message = "N/A";

    @RabbitListener(queues = "com.dorefactor.deploy.command")
    public void receiveMessage(Message message) {

        var text = new String(message.getBody(), StandardCharsets.UTF_8);

        logger.info("message received: {}", text);
        RabbitListenerFixture.message = text;
    }

    public static String getLastMessage() {

        logger.info("last message: {}", message);
        return message;
    }

}