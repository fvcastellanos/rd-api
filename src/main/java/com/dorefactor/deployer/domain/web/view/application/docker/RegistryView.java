package com.dorefactor.deployer.domain.web.view.application.docker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RegistryView {

    private boolean nonPublic;
    private String url;
    private String username;
    private String password;
}
