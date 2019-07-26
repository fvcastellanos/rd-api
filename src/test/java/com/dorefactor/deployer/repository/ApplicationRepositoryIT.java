package com.dorefactor.deployer.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationRepositoryIT extends BaseRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void testGetAll() {

        var appsFlux = applicationRepository.getAll();

        assertNotNull(appsFlux);
    }
}