package com.dorefactor.deployer.message;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitOperations;

import java.nio.charset.StandardCharsets;

import static java.util.Objects.requireNonNull;

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
    public void produce(String value) {

        var message = buildMessage(exchange, routingKey, value);
        rabbitOperations.send(message);
    }

    private Message buildMessage(String exchange, String routingKey, String value) {

        var text = requireNonNull(value);
        var body = text.getBytes(StandardCharsets.UTF_8);

        var messageProperties = new MessageProperties();
        messageProperties.setReceivedExchange(exchange);
        messageProperties.setReceivedRoutingKey(routingKey);
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentLength(body.length);

        return new Message(body, messageProperties);
    }
}
