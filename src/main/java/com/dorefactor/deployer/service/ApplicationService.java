package com.dorefactor.deployer.service;

import static com.dorefactor.deployer.service.model.ServiceError.APPLICATION_ALREADY_EXITS;
import static com.dorefactor.deployer.service.model.ServiceError.ERROR_PROCESSING;

import java.util.List;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.service.model.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.control.Either;
import io.vavr.control.Try;

public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationDao applicationDao;

    public ApplicationService(ApplicationDao applicationDao) {

        this.applicationDao = applicationDao;
    }

    public Either<Error, Application> addApplication(Application application) {

        try {
            var applicationHolder = applicationDao.getByName(application.getName());

            if (applicationHolder.isPresent()) {
    
                logger.error("application={} already exists", application);
                return Either.left(APPLICATION_ALREADY_EXITS);
            }

            var app = applicationDao.save(application);
            logger.info("application_name={} created", app.getName());
            return Either.right(app);
        } catch(Exception ex) {
            
            logger.error("can't process operation: ", ex);
            return Either.left(ERROR_PROCESSING);
        }
    }

    public Either<Error, List<Application>> getApplications() {
        
        return Try.of(() -> applicationDao.getAll())
            .onSuccess(app -> logger.info("applications retrieved successfully"))
            .onFailure(ex -> logger.error("can't get applications: ", ex))
            .toEither()
            .mapLeft(ex -> ERROR_PROCESSING);
    }
}