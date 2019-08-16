package com.dorefactor.deployer.service;

import com.dorefactor.deployer.domain.model.DeploymentTemplate;
import com.dorefactor.deployer.domain.model.HostSetup;
import com.dorefactor.deployer.domain.service.DeploymentHost;
import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.dorefactor.deployer.domain.service.DeploymentRequest;
import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.error.ServiceError;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;

import io.vavr.control.Either;

import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.domain.model.DeploymentOrder;

import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DeploymentService {

    private static final Logger logger = LoggerFactory.getLogger(DeploymentService.class);
    
    private final DeploymentOrderDao deploymentOrderDao;
    private final DeploymentTemplateDao deploymentTemplateDao;
    private final DeploymentOrderProducer deploymentOrderProducer;
    private final Gson gson;

    public DeploymentService(DeploymentOrderDao deploymentOrderDao,
                             DeploymentTemplateDao deploymentTemplateDao,
                             DeploymentOrderProducer deploymentOrderProducer,
                             Gson gson) {

        this.deploymentOrderDao = deploymentOrderDao;
        this.deploymentTemplateDao = deploymentTemplateDao;
        this.deploymentOrderProducer = deploymentOrderProducer;
        this.gson = gson;
    }

    public Either<Error, DeploymentOrder> queueDeploymentOrder(DeploymentRequest deploymentRequest) {

        try {
            logger.info("processing deployment request: {}", deploymentRequest);
            var deploymentTemplateHolder = deploymentTemplateDao.getByName(deploymentRequest.getTemplateName());

            if (deploymentTemplateHolder.isEmpty()) {

                logger.error("deployment template: {} was not found", deploymentRequest.getTemplateName());
                return Either.left(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);
            }

            var deploymentOrder = buildDeploymentOrder(deploymentRequest, deploymentTemplateHolder.get());
            deploymentOrderDao.save(deploymentOrder);
            deploymentOrderProducer.produce(gson.toJson(deploymentOrder));

            logger.info("deployment order: {} saved and queued", deploymentOrder);

            return Either.right(deploymentOrder);
        } catch (Exception ex) {

            logger.error("can't queue deployment order: {} - ", deploymentRequest, ex);
            return Either.left(ServiceError.ERROR_PROCESSING);
        }
    }

    // ------------------------------------------------------------------------------------------------------

    private static String buildRequestId() {

        return UUID.randomUUID().toString();
    }

    private static DeploymentOrder buildDeploymentOrder(DeploymentRequest deploymentRequest, DeploymentTemplate deploymentTemplate) {

        var deploymentOrder = new DeploymentOrder();
        deploymentOrder.setDeploymentTemplate(deploymentTemplate);
        deploymentOrder.setRequestId(buildRequestId());
        deploymentOrder.setVersion(deploymentRequest.getVersion());
        deploymentOrder.setCreatedAt(LocalDateTime.now()); // check if localDateTime is a good idea
        deploymentOrder.setApplication(deploymentTemplate.getApplication());
//        deploymentOrder.setHostsSetup(buildHostSetupList(deploymentRequest.getHosts(), deploymentTemplate.getHostsSetup()));

        return deploymentOrder;
    }

    private static List<HostSetup> buildHostSetupList(List<DeploymentHost> hosts, List<HostSetup> hostsSetup) {

//        hosts.stream()

        hostsSetup.stream().filter(hostSetup -> {

            return true;

        }).collect(Collectors.toList());

        return null;
    }
}