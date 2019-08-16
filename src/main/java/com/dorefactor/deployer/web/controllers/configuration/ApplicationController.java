package com.dorefactor.deployer.web.controllers.configuration;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.web.response.RequestData;
import com.dorefactor.deployer.domain.web.response.application.ApplicationResponse;
import com.dorefactor.deployer.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController extends AbstractConfigurationController {

    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public ResponseEntity getApplications() {
        var requestData = buildRequestData();

        var result = applicationService.getApplications();

        if (result.isLeft())  {

            return buildAppErrorResponse(requestData, result.getLeft());
        }

        return buildApplicationResponse(requestData, result.get());
    }

    public ResponseEntity newApplication() {

        return null;
    }

    // ------------------------------------------------------------------------------------------

    private ResponseEntity<ApplicationResponse> buildApplicationResponse(RequestData requestData, List<Application> applications) {

        var response = new ApplicationResponse();
        response.setRequest(requestData);
        response.setApplications(applications);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
