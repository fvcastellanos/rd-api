package com.dorefactor.deployer.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Host {

    private String ip;
    private String username;
    private String password;
}