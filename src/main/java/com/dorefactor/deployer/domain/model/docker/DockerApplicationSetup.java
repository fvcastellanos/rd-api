package com.dorefactor.deployer.domain.model.docker;

import java.util.Map;

import com.dorefactor.deployer.domain.model.ApplicationSetup;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@BsonDiscriminator
@EqualsAndHashCode
@ToString(callSuper = true)
public class DockerApplicationSetup extends ApplicationSetup {

    private Registry registry;
    private Image image;
    private Map<String, String> ports;
    private Map<String, String> environmentVariables;
    private Map<String, String> extraHosts;
    private Map<String, String> volumes;
}