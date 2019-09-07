package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.web.BaseWebIT;
import com.dorefactor.deployer.web.converter.ApplicationConverter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerIT extends BaseWebIT {

    @Test
    void testGetApplications() throws Exception {

        var app = newApplication();
        var expectedView = ApplicationConverter.buildApplicationView(app);

        String blah = asJson(expectedView);

        mockMvc.perform(get("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    // ---------------------------------------------------------------------------------

    private Application newApplication() {

        var application = ModelFixture.buildDockerApplication();
        return mongoTemplate.insert(application);
    }
}
