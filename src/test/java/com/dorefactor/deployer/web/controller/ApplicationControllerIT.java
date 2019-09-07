package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.web.BaseWebIT;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class ApplicationControllerIT extends BaseWebIT {

    @Test
    public void test() throws Exception {

        assertNotNull(mockMvc);
//        mockMvc.perform(MockMvcRequestBuilders.get("/configuration/applications"))
//                .andExpect(status().isOk());
    }
}
