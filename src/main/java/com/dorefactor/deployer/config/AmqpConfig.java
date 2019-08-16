package com.dorefactor.deployer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitAutoConfiguration.class)
public class AmqpConfig {

    @Value("${rd.api.rabbitmq.exchange:regular-deployer-exchange}")
    private String exchangeName;

    @Value("${rd.api.rabbitmq.queue:com.dorefactor.deploy.command}")
    private String queueName;

    @Bean
    public Exchange exchange() {

        return ExchangeBuilder.directExchange(exchangeName)
                .build();
    }

    @Bean
    public Queue commandQueue() {

        return new Queue(queueName, false);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queueName)
                .noargs();
    }
}
