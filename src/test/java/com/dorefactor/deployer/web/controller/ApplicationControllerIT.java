package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.fixture.ViewFixture;
import com.dorefactor.deployer.web.BaseWebIT;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerIT extends BaseWebIT {

    @Test
    void testGetApplications() throws Exception {

        var app = newApplication("test-app-i");
        var content = getApplicationListSample(app.getId().toString(), app.getName());

        mockMvc.perform(get("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content, false));
    }

    @Test
    void testAddApplication() throws Exception {

        var view = ViewFixture.buildApplicationView();

        var content = getNewApplicationSample();

        mockMvc.perform(post("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJson(view)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content, false));
    }

    @Test
    void testAddExistingApp() throws Exception {

        newApplication("test-app");

        var view = ViewFixture.buildApplicationView();
        var content = getAppAlreadyExistsSample();

        mockMvc.perform(post("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJson(view)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content, false));
    }

    @Test
    void testGetAppByName() throws Exception {

        var appName = "test-app";
        var app = newApplication(appName);
        var id = app.getId();

        var content = getApplicationSample(id.toString());

        mockMvc.perform(get("/configuration/applications/" + appName)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content, false));
    }

    @Test
    void testGetAppNotFund() throws Exception {

        var appName = "test-app";

        var content = getAppNotFoundErrorSample();

        mockMvc.perform(get("/configuration/applications/" + appName)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(content, false));
    }

    // ---------------------------------------------------------------------------------

    private Application newApplication(String appName) {

        var application = ModelFixture.buildDockerApplication();
        application.setName(appName);
        return mongoTemplate.insert(application);
    }

    private String getApplicationListSample(String id, String name) throws Exception {

        return loadSampleWithValues("classpath:samples/application/application-list.json", ImmutableMap.of(
                "#appId", id,
                "#appName", name
        ));
    }

    private String getNewApplicationSample() throws Exception {

        return loadSample("classpath:samples/application/new-application-response.json");
    }

    private String getApplicationSample(String id) throws Exception {

        return loadSampleWithValues("classpath:samples/application/existing-app.json",
                ImmutableMap.of("#appId", id));
    }

    private String getAppAlreadyExistsSample() throws Exception {

        return loadSample("classpath:samples/application/app-already-exists.json");
    }

    private String getAppNotFoundErrorSample() throws Exception {

        return loadSample("classpath:samples/application/app-not-found.json");
    }
}
