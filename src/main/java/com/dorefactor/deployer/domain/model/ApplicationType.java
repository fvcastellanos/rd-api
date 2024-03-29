package com.dorefactor.deployer.domain.model;

import lombok.Getter;

@Getter
public enum ApplicationType {

    DOCKER("Docker based application");

    private String description;

    private ApplicationType(String description) {
        this.description = description;
    }
}