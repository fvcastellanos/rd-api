package com.dorefactor.deployer.dao.model.docker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Registry {

    private boolean nonPublic;
    private String url;
    private String username;
    private String password;
}