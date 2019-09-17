package com.dorefactor.deployer.fixture;

import com.dorefactor.deployer.domain.model.docker.Registry;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.docker.ImageView;
import com.dorefactor.deployer.domain.web.view.application.docker.RegistryView;
import com.google.common.collect.ImmutableMap;

public class ViewFixture {

    public static RegistryView buildRegistryView() {

        var registry = new RegistryView();
        registry.setNonPublic(false);
        registry.setUsername("blah");
        registry.setPassword("****");
        registry.setUrl("dockerhub.com");

        return registry;
    }


    public static ImageView buildImageView() {

        var image = new ImageView();
        image.setName("dorefactor/rd-api");
        image.setTag("1.1");

        return image;
    }

    public static DockerApplicationSetupView buildDockerApplicationSetupView() {

        var setup = new DockerApplicationSetupView();
        setup.setType("DOCKER");
        setup.setVolumes(ImmutableMap.of("/data", "/data"));
        setup.setPorts(ImmutableMap.of("80", "80", "8080", "8080"));
        setup.setExtraHosts(ImmutableMap.of("host", "10.10.10.10"));
        setup.setEnvironmentVariables(ImmutableMap.of("foo", "var"));
        setup.setRegistry(buildRegistryView());
        setup.setImage(buildImageView());

        return setup;
    }

    public static ApplicationView buildApplicationView() {

        var view = new ApplicationView();
        view.setName("test-app");
        view.setApplicationSetup(buildDockerApplicationSetupView());

        return view;
    }
}
