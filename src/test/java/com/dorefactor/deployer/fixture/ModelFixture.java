package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.ApplicationType;
import com.dorefactor.deployer.domain.model.DeploymentOrder;
import com.dorefactor.deployer.domain.model.DeploymentTemplate;
import com.dorefactor.deployer.domain.model.Host;
import com.dorefactor.deployer.domain.model.HostSetup;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;

import java.util.Collections;

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

    public static DeploymentTemplate buildDeploymentTemplate() {

        var template = new DeploymentTemplate();
        template.setName(buildRandomName());
        template.setApplication(buildDockerApplication());
        template.setHostsSetup(Collections.singletonList(buildHostSetup()));

        return template;
    }

    public static DeploymentOrder buildDeploymentOrder() {

        var app = buildDockerApplication();
        app.setId(buildRandomObjectId());

        var template = buildDeploymentTemplate();
        template.setId(buildRandomObjectId());

        var order = new DeploymentOrder();
        order.setApplication(app);
        order.setDeploymentTemplate(template);
        order.setVersion("1.1.1");
        order.setRequestId(RandomStringUtils.randomAlphanumeric(20));
        
        return order;
    }

    // -----------------------------------------------------------------------------

    private static String buildRandomName() {

        return RandomStringUtils.randomAlphanumeric(10);
    }

    private static Host buildHost() {

        var host = new Host();
        host.setIp("server01");
        host.setUsername("user");
        host.setPassword("s3cr3t");

        return host;
    }

    private static HostSetup buildHostSetup() {

        var hostSetup = new HostSetup();
        hostSetup.setTag("tag");
        hostSetup.setHosts(Collections.singletonList(buildHost()));

        return hostSetup;
    }

}