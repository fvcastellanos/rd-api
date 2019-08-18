package com.dorefactor.deployer.web.converter;

import static java.util.Objects.isNull;

import java.util.Map;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.ApplicationSetup;
import com.dorefactor.deployer.domain.model.ApplicationType;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;
import com.dorefactor.deployer.domain.model.docker.Image;
import com.dorefactor.deployer.domain.model.docker.Registry;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.docker.ImageView;
import com.dorefactor.deployer.domain.web.view.application.docker.RegistryView;
import com.google.common.collect.ImmutableMap;

public class ApplicationConverter {

    private static final Map<ApplicationType, ApplicationSetup> appTypeMap = 
        ImmutableMap.of(
            ApplicationType.DOCKER, 
        );

    private static ApplicationView buildApplicationView(Application application) {
        
        var view = new ApplicationView();
        view.setId(application.getId().toString());
        view.setName(application.getName());

        var applicationSetup = application.getApplicationSetup();


        return view;
    }

    private static ApplicationSetup getApplicationSetup(ApplicationSetup applicationSetup) {

    }

    private static DockerApplicationSetupView buildDockerApplicationSetupView(DockerApplicationSetup dockerApplicationSetup) {

        var imageView = buildImageView(dockerApplicationSetup.getImage());
        var registryView = buildRegistryView(dockerApplicationSetup.getRegistry());

        var view = new DockerApplicationSetupView();
        view.setType(dockerApplicationSetup.getApplicationType().name());
        view.setImageView(imageView);
        view.setRegistryView(registryView);
        view.setEnvironmentVariables(dockerApplicationSetup.getEnvironmentVariables());
        view.setExtraHosts(dockerApplicationSetup.getExtraHosts());
        view.setPorts(dockerApplicationSetup.getPorts());
        view.setVolumes(dockerApplicationSetup.getVolumes());

        return view;
    }

    private static RegistryView buildRegistryView(Registry registry) {

        if (isNull(registry)) {

            return null;
        }

        var registryView = new RegistryView();
        registryView.setNonPublic(registry.isNonPublic());
        registryView.setUrl(registry.getUrl());
        registryView.setUsername(registry.getUsername());
        registryView.setPassword(registry.getPassword());

        return registryView;
    }

    private static ImageView buildImageView(Image image) {

        if (isNull(image)) {

            return null;
        }

        var imageView = new ImageView();
        imageView.setName(image.getName());
        imageView.setTag(image.getTag());                

        return imageView;
    }
}