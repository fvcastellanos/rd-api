package com.dorefactor.deployer.service;

import java.util.List;

import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.service.model.Error;
import com.dorefactor.deployer.service.model.ServiceError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.control.Either;
import io.vavr.control.Try;

public class DeploymentTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(DeploymentTemplateService.class);

    private final DeploymentTemplateDao deploymentTemplateDao;

    public DeploymentTemplateService(DeploymentTemplateDao deploymentTemplateDao) {

        this.deploymentTemplateDao = deploymentTemplateDao;
    }

    public Either<Error, DeploymentTemplate> add(DeploymentTemplate deploymentTemplate) {
        
        try {

            logger.info("creating new deployment template: ", deploymentTemplate);

            var deploymentTemplateHolder = deploymentTemplateDao.getByName(deploymentTemplate.getName());

            if (deploymentTemplateHolder.isPresent()) {

                logger.info("deployment template with name: {} already exists", deploymentTemplate.getName());
                return Either.left(ServiceError.DEPLOYMENT_TEMPLATE_ALREADY_EXISTS);
            }

            var template = deploymentTemplateDao.save(deploymentTemplate);
            logger.info("deployment template: {} created successfully", deploymentTemplate);

            return Either.right(template);
            
        } catch(Exception ex) {

            logger.error("can't add new deployment template, ", ex);
            return Either.left(ServiceError.ERROR_PROCESSING);
        }
    }

    public Either<Error, List<DeploymentTemplate>> getAll() {

        return Try.of(() -> deploymentTemplateDao.getAll())
            .onSuccess(templates -> logger.info("deployment templates retrieved successfully"))
            .onFailure(ex -> logger.error("can't get deployment templates, ", ex))
            .toEither()
            .mapLeft(ex -> ServiceError.ERROR_PROCESSING);
    }

    public Either<Error, DeploymentTemplate> getByName(String name) {

        try {

            var templateHolder = deploymentTemplateDao.getByName(name);

            if (templateHolder.isEmpty()) {

                logger.error("deployment template with name: {} not found", name);
                return Either.left(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);
            }

            logger.info("deployment template: {} found", name);
            return Either.right(templateHolder.get());

        } catch(Exception ex) {

            logger.error("can't get deployment template, ", ex);
            return Either.left(ServiceError.ERROR_PROCESSING);
        }
    }


}