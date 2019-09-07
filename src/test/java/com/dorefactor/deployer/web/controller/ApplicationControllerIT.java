package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.web.BaseWebIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerIT extends BaseWebIT {

    @Test
    void test() throws Exception {

//        newApplication();
        mockMvc.perform(
                get("/configuration/applications")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    private void newApplication() {

        var application = ModelFixture.buildDockerApplication();
        mongoTemplate.insert(application);
    }
}
