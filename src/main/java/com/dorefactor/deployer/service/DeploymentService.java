package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.dorefactor.deployer.service.model.DeploymentRequest;
import com.dorefactor.deployer.service.model.Error;
import com.dorefactor.deployer.service.model.ServiceError;
import org.slf4j.LoggerFactory;

import io.vavr.control.Either;

import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.dao.model.DeploymentOrder;

import org.slf4j.Logger;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class DeploymentService {

    private static final Logger logger = LoggerFactory.getLogger(DeploymentService.class);
    
    private final DeploymentOrderDao deploymentOrderDao;
    private final DeploymentTemplateDao deploymentTemplateDao;
    private final DeploymentOrderProducer deploymentOrderProducer;

    public DeploymentService(DeploymentOrderDao deploymentOrderDao,
                             DeploymentTemplateDao deploymentTemplateDao,
                             DeploymentOrderProducer deploymentOrderProducer) {

        this.deploymentOrderDao = deploymentOrderDao;
        this.deploymentTemplateDao = deploymentTemplateDao;
        this.deploymentOrderProducer = deploymentOrderProducer;
    }

    public Either<Error, DeploymentOrder> queueDeploymentOrder(DeploymentRequest deploymentRequest) {

        logger.info("processing deployment request: {}", deploymentRequest);
        var deploymentTemplateHolder = deploymentTemplateDao.getByName(deploymentRequest.getTemplateName());

        if (deploymentTemplateHolder.isEmpty()) {

            logger.error("deployment template: {} was not found", deploymentRequest.getTemplateName());
            return Either.left(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);
        }

        var deploymentOrder = buildDeploymentOrder(deploymentTemplateHolder.get());
        deploymentOrderDao.save(deploymentOrder);

        return Either.right(deploymentOrder);
    }

    // ------------------------------------------------------------------------------------------------------

    private static String buildRequestId() {

        return UUID.randomUUID().toString();
    }

    private static DeploymentOrder buildDeploymentOrder(DeploymentTemplate deploymentTemplate) {

        var deploymentOrder = new DeploymentOrder();
        deploymentOrder.setDeploymentTemplate(deploymentTemplate);
        deploymentOrder.setRequestId(buildRequestId());
        deploymentOrder.setCreatedAt(LocalDateTime.now()); // check if localDateTime is a good idea
        deploymentOrder.setApplication(deploymentTemplate.getApplication());

        return deploymentOrder;
    }
}