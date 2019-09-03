package com.dorefactor.deployer.web;

import com.dorefactor.deployer.config.MongoDbConfig;
import com.dorefactor.deployer.web.config.WebITConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = WebITConfig.class)
//@ContextConfiguration(classes = WebITConfig.class)
@DataMongoTest(excludeAutoConfiguration = {
        MongoDbConfig.class
})
public abstract class BaseWebIT {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected WebApplicationContext context;
}
