package com.dorefactor.deployer.web;

import com.dorefactor.deployer.web.config.WebITConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringJUnitWebConfig(classes = WebITConfig.class)
public abstract class BaseWebIT {

    private static final Gson gson = new Gson();

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MockMvc mockMvc;

    protected <T> String asJson(T object) {

        return gson.toJson(object);
    }
}
