package com.dorefactor.deployer.domain.web.view.application.docker;

import com.dorefactor.deployer.domain.web.view.application.ApplicationSetupView;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DockerApplicationSetupView extends ApplicationSetupView {

    private RegistryView registryView;
    private ImageView imageView;
    private Map<String, String> ports;
    private Map<String, String> environmentVariables;
    private Map<String, String> extraHosts;
    private Map<String, String> volumes;
}
