package com.dorefactor.deployer.web.controller.configuration;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.model.docker.DockerApplicationSetup;
import com.dorefactor.deployer.domain.web.view.RequestDataView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationResponseView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationSetupView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.docker.DockerApplicationSetupView;
import com.dorefactor.deployer.service.ApplicationService;
import com.dorefactor.deployer.web.converter.ApplicationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/applications")
    public ResponseEntity newApplication(ApplicationView applicationView) {

        var requestData = buildRequestData();

        // add validators


        return null;
    }

    // ------------------------------------------------------------------------------------------

    private ResponseEntity<ApplicationResponseView> buildApplicationResponse(RequestDataView requestDataView, List<Application> applications) {

        var applicationViewList = applications.stream()
                .map(ApplicationConverter::buildApplicationView)
                .collect(Collectors.toList());

        var response = new ApplicationResponseView();
        response.setRequest(requestDataView);
        response.setApplications(applicationViewList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
