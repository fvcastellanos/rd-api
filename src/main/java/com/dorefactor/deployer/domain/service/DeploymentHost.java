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
public class DeploymentHost {

    private String tag;
    private List<String> hosts;
}
