package com.dorefactor.deployer.service;

import org.slf4j.LoggerFactory;

import io.vavr.control.Either;

import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.dao.model.DeploymentOrder;

import org.slf4j.Logger;

public class DeploymentService {

    private static final Logger logger = LoggerFactory.getLogger(DeploymentService.class);
    
    private final DeploymentOrderDao deploymentOrderDao;
    private final DeploymentTemplateDao deploymentTemplateDao;

    public DeploymentService(DeploymentOrderDao deploymentOrderDao, DeploymentTemplateDao deploymentTemplateDao) {

        this.deploymentOrderDao = deploymentOrderDao;
        this.deploymentTemplateDao = deploymentTemplateDao;
    }

    public Either<Error, DeploymentOrder> queueDeploymentOrder(DeploymentOrder deploymentOrder) {

        return null;
    }

}