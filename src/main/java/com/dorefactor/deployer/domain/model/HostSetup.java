package com.dorefactor.deployer.domain.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HostSetup {

    private String tag;
    private List<Host> hosts;
}