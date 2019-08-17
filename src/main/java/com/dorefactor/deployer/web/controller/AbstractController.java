package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.web.view.ApplicationErrorView;
import com.dorefactor.deployer.domain.web.view.RequestDataView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public abstract class AbstractController {

    protected ResponseEntity<ApplicationErrorView> buildAppErrorResponse(RequestDataView requestDataView, Error error) {

        return new ResponseEntity<>(buildAppError(requestDataView, error), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    protected RequestDataView buildRequestData() {

        var data = new RequestDataView();
        data.setId(UUID.randomUUID().toString());
        data.setTime(Instant.now(Clock.systemUTC()));

        return data;
    }

    // -------------------------------------------------------------------------------------

    private ApplicationErrorView buildAppError(RequestDataView requestDataView, Error error) {

        var appError = new ApplicationErrorView();
        appError.setRequest(requestDataView);
        appError.setMessage(error.getMessage());

        return appError;
    }
}
