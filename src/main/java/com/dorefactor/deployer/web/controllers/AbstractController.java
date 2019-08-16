package com.dorefactor.deployer.web.controllers;

import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.web.response.AppErrorResponse;
import com.dorefactor.deployer.domain.web.response.RequestData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public abstract class AbstractController {

    protected ResponseEntity<AppErrorResponse> buildAppErrorResponse(RequestData requestData, Error error) {

        return new ResponseEntity<>(buildAppError(requestData, error), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    protected RequestData buildRequestData() {

        var data = new RequestData();
        data.setId(UUID.randomUUID().toString());
        data.setTime(Instant.now(Clock.systemUTC()));

        return data;
    }

    // -------------------------------------------------------------------------------------

    private AppErrorResponse buildAppError(RequestData requestData, Error error) {

        var appError = new AppErrorResponse();
        appError.setRequest(requestData);
        appError.setMessage(error.getMessage());

        return appError;
    }
}
