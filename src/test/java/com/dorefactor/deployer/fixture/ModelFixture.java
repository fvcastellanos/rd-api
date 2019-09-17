package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.ApplicationType;
import com.dorefactor.deployer.domain.model.DeploymentOrder;
import com.dorefactor.deployer.domain.model.DeploymentTemplate;
import com.dorefactor.deployer.domain.model.Host;
import com.dorefactor.deployer.domain.model.HostSetup;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;

import com.dorefactor.deployer.domain.model.docker.Image;
import com.dorefactor.deployer.domain.model.docker.Registry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;

import java.util.Collections;

public class ModelFixture {

    public static ObjectId buildRandomObjectId() {

        return new ObjectId(RandomStringUtils.randomNumeric(12).getBytes());
    }

    public static Registry buildRegistry() {

        var registry = new Registry();
        registry.setNonPublic(false);
        registry.setUsername("blah");
        registry.setPassword("****");
        registry.setUrl("dockerhub.com");

        return registry;
    }

    public static Image buildImage() {

        var image = new Image();
        image.setName("dorefactor/rd-api");
        image.setTag("1.1");

        return image;
    }

    public static DockerApplicationSetup buildDockerApplicationSetup() {

        var setup = new DockerApplicationSetup();
        setup.setApplicationType(ApplicationType.DOCKER);
        setup.setVolumes(ImmutableMap.of("/data", "/data"));
        setup.setPorts(ImmutableMap.of("80", "80", "8080", "8080"));
        setup.setExtraHosts(ImmutableMap.of("host", "10.10.10.10"));
        setup.setEnvironmentVariables(ImmutableMap.of("foo", "var"));
        setup.setRegistry(buildRegistry());
        setup.setImage(buildImage());

        return setup;
    }

    public static Application buildDockerApplication() {


        var application = new Application();
        application.setName(buildRandomName());
        application.setApplicationSetup(buildDockerApplicationSetup());

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

    public static Host buildHost(String ip) {

        var host = new Host();
        host.setIp(ip);
        host.setUsername("user");
        host.setPassword("s3cr3t");

        return host;
    }

    public static Host buildHost() {

        return buildHost("server01");
    }

    public static HostSetup buildHostSetup() {

        return buildHostSetup("tag");
    }

    public static HostSetup buildHostSetup(String tag) {

        var hostSetup = new HostSetup();
        hostSetup.setTag(tag);
        hostSetup.setHosts(Collections.singletonList(buildHost()));

        return hostSetup;
    }

    // -----------------------------------------------------------------------------

    private static String buildRandomName() {

        return RandomStringUtils.randomAlphanumeric(10);
    }
}