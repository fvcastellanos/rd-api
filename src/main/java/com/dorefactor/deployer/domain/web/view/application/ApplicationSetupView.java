package com.dorefactor.deployer.domain.web.view.application;

import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DockerApplicationSetupView.class)
})
public abstract class ApplicationSetupView {

    private String type;
}
