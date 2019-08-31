package com.dorefactor.deployer.dao;

import com.dorefactor.deployer.config.BaseDaoITConfig;
import com.dorefactor.deployer.config.MongoDbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@EnableAutoConfiguration
@SpringJUnitConfig(classes = BaseDaoITConfig.class)
@DataMongoTest(excludeAutoConfiguration = {
        MongoDbConfig.class
})
public abstract class BaseDaoIT {

    @Autowired
    protected MongoOperations mongoTemplate;

}
