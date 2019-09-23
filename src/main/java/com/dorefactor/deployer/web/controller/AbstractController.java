package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.web.view.ApplicationErrorView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    protected ResponseEntity<ApplicationErrorView> buildErrorResponse(HttpStatus status, Error error) {

        var errorView = new ApplicationErrorView();
        errorView.setMessage(error.getMessage());
        return new ResponseEntity<>(errorView, status);
    }

    protected ResponseEntity<ApplicationErrorView> buildAppErrorResponse(Error error) {

        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    protected ResponseEntity<ApplicationErrorView> buildNotFoundResponse(Error error) {

        return buildErrorResponse(HttpStatus.NOT_FOUND, error);
    }

}
