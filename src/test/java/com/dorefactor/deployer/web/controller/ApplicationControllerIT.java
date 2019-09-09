package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.web.BaseWebIT;
import com.dorefactor.deployer.web.converter.ApplicationConverter;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerIT extends BaseWebIT {

    @Test
    void testGetApplications() throws Exception {

        var apps = Lists.newArrayList(newApplication("test-app-i"),
                newApplication("test-app-ii"));

        var views = apps.stream()
                .map(ApplicationConverter::buildApplicationView)
                .collect(Collectors.toList());

//        var content = asJson(views);

        var content = getApplicationListSample("asdf");

        mockMvc.perform(get("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.request.id").isNotEmpty())
                .andExpect(jsonPath("$.request.time").isNotEmpty())
                .andExpect(jsonPath("$.applications[*]").isArray())
//                .andExpect(jsonPath("$.applications").value(content))
        ;
    }

    // ---------------------------------------------------------------------------------

    private Application newApplication(String appName) {

        var application = ModelFixture.buildDockerApplication();
        application.setName(appName);
        return mongoTemplate.insert(application);
    }

    private String getApplicationListSample(String id) throws Exception {

        var sample = loadSample("classpath:samples/application/application-list.json");
        return sample.replaceAll("#appId", id);
    }
}
