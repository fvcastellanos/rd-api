package com.dorefactor.deployer.domain.web.view.application.docker;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistryView {

    private boolean nonPublic;
    private String url;
    private String username;
    private String password;
}
