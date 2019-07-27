package com.dorefactor.deployer.dao;

import com.dorefactor.deployer.configuration.MongoDbConfig;
import com.dorefactor.deployer.dao.configuration.BaseDaoITConfig;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = BaseDaoITConfig.class)
@DataMongoTest(excludeAutoConfiguration = {
    MongoDbConfig.class
})
public abstract class BaseDaoIT {

    @Autowired
    protected MongoOperations mongoTemplate;
}