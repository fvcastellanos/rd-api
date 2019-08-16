package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.domain.service.DeploymentHost;
import com.dorefactor.deployer.domain.service.DeploymentRequest;

import java.util.Collections;

public class ServiceModelFixture {

    public static DeploymentRequest buildDeploymentRequest(String templateName) {

        var request = new DeploymentRequest();

        request.setTemplateName(templateName);
        request.setVersion("1.0");
        request.setDeploymentHosts(Collections.singletonList(buildDeploymentHost()));

        return request;
    }

    private static DeploymentHost buildDeploymentHost() {

        var host = new DeploymentHost();
        host.setTag("tag");
        host.setHosts(Collections.singletonList("server01"));

        return host;
    }
}
