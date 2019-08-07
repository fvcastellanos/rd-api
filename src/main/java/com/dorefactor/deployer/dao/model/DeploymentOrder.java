package com.dorefactor.deployer.dao.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document("deploymentOrders")
public class DeploymentOrder {

    private ObjectId id;
    private String requestId;
    private LocalDateTime createdAt;
    private DeploymentTemplate deploymentTemplate;
    private Application application;
    private List<HostSetup> hostsSetup;
}