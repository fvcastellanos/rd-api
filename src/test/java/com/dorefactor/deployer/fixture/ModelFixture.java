package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.dao.model.ApplicationType;
import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.dao.model.docker.DockerApplicationSetup;

import org.apache.commons.lang3.RandomStringUtils;

public class ModelFixture {

    public static Application buildDockerApplication() {

        var setup = new DockerApplicationSetup();
        setup.setApplicationType(ApplicationType.DOCKER);

        var application = new Application();
        application.setName(buildRandomName());
        application.setApplicationSetup(setup);

        return application;
    }

    public static DeploymentTemplate builDeploymentTemplate() {

        var template = new DeploymentTemplate();
        template.setName(buildRandomName());
        template.setApplication(buildDockerApplication());

        return template;
    }

    // -----------------------------------------------------------------------------

    private static String buildRandomName() {

        return RandomStringUtils.randomAlphanumeric(10);
    }

}