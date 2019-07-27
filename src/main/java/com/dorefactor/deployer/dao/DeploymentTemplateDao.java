package com.dorefactor.deployer.dao;

import java.util.List;
import java.util.Optional;

import com.dorefactor.deployer.dao.model.DeploymentTemplate;

import org.bson.types.ObjectId;

public interface DeploymentTemplateDao {

    List<DeploymentTemplate> getAll();
    DeploymentTemplate save(DeploymentTemplate deploymentTemplate);
    Optional<DeploymentTemplate> getByName(String name);
    Optional<DeploymentTemplate> getById(ObjectId id);    
}