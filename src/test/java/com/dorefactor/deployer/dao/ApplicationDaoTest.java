package com.dorefactor.deployer.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.dao.model.ApplicationType;
import com.dorefactor.deployer.dao.model.docker.DockerApplicationSetup;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationDaoTest extends BaseDaoIT {

    @Autowired
    private ApplicationMongoDao applicationRepository;

    @Test
    public void testGetAll() {

        var applications = Lists.newArrayList(saveRandomApplication(), saveRandomApplication());

        var apps = applicationRepository.getAll();

        assertThat(apps).containsAll(applications);
    }

    @Test
    public void testGetByNameWithExistingApp() {
        
        var application = saveRandomApplication();

        var appHolder = applicationRepository.getByName(application.getName());

        assertThat(appHolder).get()
            .isEqualTo(application);
    }

    @Test
    public void testGetByNameNonExistingApp() {

        var appHolder = applicationRepository.getByName("not-existing-app");

        assertThat(appHolder).isEmpty();
    }

    // ------------------------------------------------------------------------------------

    private Application saveRandomApplication() {

        return mongoTemplate.save(buildDockerApplication());
    }

    private Application buildDockerApplication() {

        var setup = new DockerApplicationSetup();
        setup.setApplicationType(ApplicationType.DOCKER);

        var application = new Application();
        application.setName(RandomStringUtils.randomAlphanumeric(10));
        application.setApplicationSetup(setup);

        return application;
    }
}