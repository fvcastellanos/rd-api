package com.dorefactor.deployer.domain.web.view.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationView {

    private String id;
    private String name;
    private ApplicationSetupView applicationSetup;
}
