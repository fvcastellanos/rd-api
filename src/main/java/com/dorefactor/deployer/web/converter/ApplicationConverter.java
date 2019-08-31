package com.dorefactor.deployer.web.converter;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.ApplicationSetup;
import com.dorefactor.deployer.domain.model.ApplicationType;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;
import com.dorefactor.deployer.domain.model.docker.Image;
import com.dorefactor.deployer.domain.model.docker.Registry;
import com.dorefactor.deployer.domain.web.view.application.ApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.docker.ImageView;
import com.dorefactor.deployer.domain.web.view.application.docker.RegistryView;
import org.bson.types.ObjectId;

import static com.dorefactor.deployer.domain.model.ApplicationType.DOCKER;
import static java.util.Objects.isNull;

public class ApplicationConverter {

    public static ApplicationView buildApplicationView(Application application) {

        var view = new ApplicationView();
        view.setId(application.getId().toString());
        view.setName(application.getName());

        var applicationSetupView = buildApplicationSetupView(application.getApplicationSetup());
        view.setApplicationSetup(applicationSetupView);

        return view;
    }

    public static Application buildApplication(ApplicationView applicationView) {

        var application = new Application();
        application.setId(new ObjectId(applicationView.getId()));
        application.setName(applicationView.getName());

        var applicationSetup = buildApplicationSetup(applicationView.getApplicationSetup());
        application.setApplicationSetup(applicationSetup);

        return application;
    }

    // ------------------------------------------------------------------------------------------------------------

    private static ApplicationSetupView buildApplicationSetupView(ApplicationSetup applicationSetup) {

        if (isNull(applicationSetup)) {

            return null;
        }

        if (applicationSetup.getApplicationType().equals(DOCKER)) {

            var dockerAppSetup = (DockerApplicationSetup) applicationSetup;
            return buildDockerApplicationSetupView(dockerAppSetup);
        }

        throw new RuntimeException("Application Type Not Implemented");
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

    // ------------------------------------------------------------------------------------------------------------

    private static ApplicationSetup buildApplicationSetup(ApplicationSetupView applicationSetupView) {

        if (isNull(applicationSetupView)) {

            return null;
        }

        if (applicationSetupView.getType().equals(DOCKER.name())) {

            var view = (DockerApplicationSetupView) applicationSetupView;
            return buildDockerApplicationSetup(view);
        }

        throw new RuntimeException("Application Type Not Implemented");
    }

    private static DockerApplicationSetup buildDockerApplicationSetup(DockerApplicationSetupView dockerApplicationSetupView) {

        var image = buildImage(dockerApplicationSetupView.getImageView());
        var registry = buildRegistry(dockerApplicationSetupView.getRegistryView());

        var view = new DockerApplicationSetup();
        view.setApplicationType(ApplicationType.valueOf(dockerApplicationSetupView.getType()));
        view.setImage(image);
        view.setRegistry(registry);
        view.setEnvironmentVariables(dockerApplicationSetupView.getEnvironmentVariables());
        view.setExtraHosts(dockerApplicationSetupView.getExtraHosts());
        view.setPorts(dockerApplicationSetupView.getPorts());
        view.setVolumes(dockerApplicationSetupView.getVolumes());

        return view;
    }

    private static Registry buildRegistry(RegistryView registryView) {

        if (isNull(registryView)) {

            return null;
        }

        var registry = new Registry();
        registry.setNonPublic(registryView.isNonPublic());
        registry.setUrl(registryView.getUrl());
        registry.setUsername(registryView.getUsername());
        registry.setPassword(registryView.getPassword());

        return registry;
    }


    private static Image buildImage(ImageView imageView) {

        if (isNull(imageView)) {

            return null;
        }

        var image = new Image();
        image.setName(imageView.getName());
        image.setTag(imageView.getTag());

        return image;
    }
}