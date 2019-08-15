package com.dorefactor.deployer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Value("${rd.api.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rd.api.rabbitmq.queue}")
    private String queueName;

    @Bean
    public Exchange exchange() {

        return ExchangeBuilder.directExchange(exchangeName)
            .build();
    }

    @Bean
    public Queue commandQueue() {

        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {

        return new BindingBuilder.bind(queue)
            .to(exchange);
    }

}