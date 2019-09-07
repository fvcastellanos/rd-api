package com.dorefactor.deployer.domain.web.view.application.docker;

import com.dorefactor.deployer.domain.web.view.application.ApplicationSetupView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DockerApplicationSetupView extends ApplicationSetupView {

    private RegistryView registry;
    private ImageView image;

    private Map<String, String> ports;
    private Map<String, String> environmentVariables;
    private Map<String, String> extraHosts;
    private Map<String, String> volumes;
}
