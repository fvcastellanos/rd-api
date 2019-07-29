package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.service.model.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.control.Either;

import static com.dorefactor.deployer.service.model.ServiceError.APPLICATION_ALREADY_EXITS;

public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationDao applicationDao;

    public ApplicationService(ApplicationDao applicationDao) {

        this.applicationDao = applicationDao;
    }

    public Either<Error, Application> addApplication(Application application) {

        var applicationHolder = applicationDao.getByName(application.getName());

        if (applicationHolder.isPresent()) {

            return Either.left(APPLICATION_ALREADY_EXITS);
        }

        return null;
    }
}