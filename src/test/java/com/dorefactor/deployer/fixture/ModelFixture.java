package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.dao.model.ApplicationType;
import com.dorefactor.deployer.dao.model.DeploymentOrder;
import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.dao.model.docker.DockerApplicationSetup;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;

public class ModelFixture {

    public static ObjectId buildRandomObjectId() {

        return new ObjectId(RandomStringUtils.randomNumeric(12).getBytes());
    }

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

    public static DeploymentOrder buildDeploymentOrder() {

        var app = buildDockerApplication();
        app.setId(buildRandomObjectId());

        var template = builDeploymentTemplate();
        template.setId(buildRandomObjectId());

        var order = new DeploymentOrder();
        order.setApplication(app);
        order.setDeploymentTemplate(template);
        order.setRequestId(RandomStringUtils.randomAlphanumeric(20));
        
        return order;
    }

    // -----------------------------------------------------------------------------

    private static String buildRandomName() {

        return RandomStringUtils.randomAlphanumeric(10);
    }

}