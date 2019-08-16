package com.dorefactor.deployer.domain.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeploymentRequest {

    private String templateName;
    private String version;
    private List<DeploymentHost> deploymentHosts;
}
