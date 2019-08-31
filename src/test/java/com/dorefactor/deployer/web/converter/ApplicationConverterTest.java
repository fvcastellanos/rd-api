package com.dorefactor.deployer.web.converter;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.ApplicationType;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;
import com.dorefactor.deployer.domain.model.docker.Image;
import com.dorefactor.deployer.domain.model.docker.Registry;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.docker.ImageView;
import com.dorefactor.deployer.domain.web.view.application.docker.RegistryView;
import com.google.common.collect.ImmutableMap;
import org.bson.types.ObjectId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationConverterTest {

    @Test
    public void testConvertApplicationToApplicationView() {

        var expectedView = buildDockerApplicationView();
        var view = ApplicationConverter.buildApplicationView(buildDockerApplication());

        assertThat(view).isNotNull()
                .isEqualToComparingFieldByField(expectedView);
    }

    @Test
    public void testConvertAppViewToApp() {

        var expectedApp = buildDockerApplication();
        var app = ApplicationConverter.buildApplication(buildDockerApplicationView());

        assertThat(app).isNotNull()
                .isEqualToComparingFieldByField(expectedApp);
    }

    // ------------------------------------------------------------------------------------------

    private Application buildDockerApplication() {

        var dockerImage = new Image();
        dockerImage.setName("docker-image");
        dockerImage.setTag("tag");

        var registry = new Registry();
        registry.setNonPublic(true);
        registry.setUrl("localhost");
        registry.setUsername("user");
        registry.setPassword("***");

        var dockerSetup = new DockerApplicationSetup();
        dockerSetup.setApplicationType(ApplicationType.DOCKER);
        dockerSetup.setEnvironmentVariables(ImmutableMap.of("FOO", "value"));
        dockerSetup.setExtraHosts(ImmutableMap.of("bar-host", "127.0.0.1"));
        dockerSetup.setPorts(ImmutableMap.of("8080", "8080"));
        dockerSetup.setVolumes(ImmutableMap.of("/volume", "/volume"));
        dockerSetup.setImage(dockerImage);
        dockerSetup.setRegistry(registry);

        var application = new Application();
        application.setApplicationSetup(dockerSetup);
        application.setName("test-application");
        application.setId(new ObjectId("5d6ab70b4e9eb268b509019f"));

        return application;
    }

    private ApplicationView buildDockerApplicationView() {

        var dockerImage = new ImageView();
        dockerImage.setName("docker-image");
        dockerImage.setTag("tag");

        var registry = new RegistryView();
        registry.setNonPublic(true);
        registry.setUrl("localhost");
        registry.setUsername("user");
        registry.setPassword("***");

        var dockerSetup = new DockerApplicationSetupView();
        dockerSetup.setType("DOCKER");
        dockerSetup.setEnvironmentVariables(ImmutableMap.of("FOO", "value"));
        dockerSetup.setExtraHosts(ImmutableMap.of("bar-host", "127.0.0.1"));
        dockerSetup.setPorts(ImmutableMap.of("8080", "8080"));
        dockerSetup.setVolumes(ImmutableMap.of("/volume", "/volume"));
        dockerSetup.setImageView(dockerImage);
        dockerSetup.setRegistryView(registry);

        var view = new ApplicationView();
        view.setId("5d6ab70b4e9eb268b509019f");
        view.setName("test-application");
        view.setApplicationSetup(dockerSetup);

        return view;
    }
}
