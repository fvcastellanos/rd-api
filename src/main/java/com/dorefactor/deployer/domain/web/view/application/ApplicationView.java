package com.dorefactor.deployer.domain.web.view.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ApplicationView {

    private String id;
    private String name;
//    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
    private ApplicationSetupView applicationSetup;
}
