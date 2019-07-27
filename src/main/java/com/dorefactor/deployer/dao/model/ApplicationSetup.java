package com.dorefactor.deployer.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class ApplicationSetup {

    private ApplicationType applicationType;
}