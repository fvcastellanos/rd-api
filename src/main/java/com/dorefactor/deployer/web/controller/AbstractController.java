package com.dorefactor.deployer.web.controller;

import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.web.view.ApplicationErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    protected ResponseEntity<ApplicationErrorView> buildAppErrorResponse(Error error) {

        var errorView = new ApplicationErrorView();
        errorView.setMessage(error.getMessage());
        return new ResponseEntity<>(errorView, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
