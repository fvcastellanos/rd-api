package com.dorefactor.deployer.web;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.dorefactor.deployer.web.config.WebITConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringJUnitWebConfig(classes = WebITConfig.class)
public abstract class BaseWebIT {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MockMvc mockMvc;

    protected <T> String asJson(T object) throws Exception {

        return objectMapper.writeValueAsString(object);
    }

    protected String loadSample(String path) throws Exception {

        var file = ResourceUtils.getFile(path);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    protected String loadSampleWithValues(String path, Map<String, String> values) throws Exception {

        var sample = loadSample(path);

        for (var entry : values.entrySet()) {

            sample = sample.replaceAll(entry.getKey(), entry.getValue());
        }

        return sample;
    }
}
