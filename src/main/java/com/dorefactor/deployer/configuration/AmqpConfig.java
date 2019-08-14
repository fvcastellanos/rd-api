package com.dorefactor.deployer.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Value("${spring.rabbitmq.host:rabbitmq-host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.username:guest}")
    private String rabbitUser;

    @Value("${spring.rabbitmq.password:guest}")
    private String rabbitPassword;

    @Value("${spring.rabbitmq.port:5672}")
    private String rabbitPort;

    @Bean
    public RabbitConnectionFactoryBean connectionFactory() {

        var factory = new RabbitConnectionFactoryBean();
        factory.setHost(rabbitHost);
        factory.setUsername(rabbitUser);
        factory.setPassword(rabbitPassword);
        factory.setPort(Integer.parseInt(rabbitPort));

        return factory;
    }

    @Bean
    public RabbitOperations rabbitOperations(ConnectionFactory connectionFactory) {

        return new RabbitTemplate(connectionFactory);
    }
}