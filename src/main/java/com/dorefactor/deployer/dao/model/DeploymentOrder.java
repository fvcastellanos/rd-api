package com.dorefactor.deployer.dao.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeploymentOrder {

    private ObjectId id;
    private String requestId;
    private LocalDateTime createdAt;
    private ObjectId deploymentTemplateId;
    private Application application;
    public List<HostSetup> hostsSetup;
}