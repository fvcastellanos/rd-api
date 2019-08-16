package com.dorefactor.deployer.domain.model;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document("deploymentTemplates")
public class DeploymentTemplate {

    @BsonId
    private ObjectId id;
    private String name;
    private Application application;
    private List<HostSetup> hostsSetup;
}