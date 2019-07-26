package com.dorefactor.deployer.repository;

import com.dorefactor.deployer.configuration.MongoDeployerConfig;
import com.dorefactor.deployer.repository.configuration.BaseRepositoryITConfig;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = BaseRepositoryITConfig.class)
@DataMongoTest(excludeAutoConfiguration = {
    MongoDeployerConfig.class
})
public abstract class BaseRepositoryIT {
}