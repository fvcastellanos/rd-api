package com.dorefactor.deployer.dao;

import java.util.Optional;

import com.dorefactor.deployer.dao.model.DeploymentOrder;

public interface DeploymentOrderDao {

    DeploymentOrder save(DeploymentOrder deploymentOrder);
    Optional<DeploymentOrder> getByRequestId(String requestId);
}