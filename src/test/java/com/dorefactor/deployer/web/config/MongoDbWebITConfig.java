package com.dorefactor.deployer.web.config;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDbWebITConfig {

    private static final String MONGO_BIND_IP = "localhost";

    @Bean
    public MongoOperations mongoOperations() throws Exception {

        var factory = new EmbeddedMongoFactoryBean();
        factory.setBindIp(MONGO_BIND_IP);
        var mongoClient = factory.getObject();

        return new MongoTemplate(mongoClient, "RdApiTest");
    }
}
