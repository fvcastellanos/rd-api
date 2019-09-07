package com.dorefactor.deployer.web;

import com.dorefactor.deployer.web.config.WebITConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringJUnitWebConfig(classes = WebITConfig.class)
public abstract class BaseWebIT {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;

    protected MockMvc mockMvc() {

        return MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

}
