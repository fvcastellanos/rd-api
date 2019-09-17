package com.dorefactor.deployer.web.controller.configuration;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.web.view.HrefView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationListResponseView;
import com.dorefactor.deployer.domain.web.view.application.ApplicationView;
import com.dorefactor.deployer.domain.web.view.application.NewApplicationResponseView;
import com.dorefactor.deployer.service.ApplicationService;
import com.dorefactor.deployer.web.converter.ApplicationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        var result = applicationService.getApplications();

        if (result.isLeft())  {

            return buildAppErrorResponse(result.getLeft());
        }

        return buildApplicationsResponse(result.get());
    }

    @GetMapping("/applications/{name}")
    public ResponseEntity getApplication(@PathVariable("name") String name) {

//        var result = applicationService.get
        return null;
    }

    @PostMapping("/applications")
    public ResponseEntity newApplication(@RequestBody ApplicationView applicationView) {

        // add validators

        var application = ApplicationConverter.buildApplication(applicationView);

        var result = applicationService.addApplication(application);

        if (result.isLeft()) {

            return buildAppErrorResponse(result.getLeft());
        }

        return buildNewApplicationResponse(applicationView);
    }

    // ------------------------------------------------------------------------------------------

    private ResponseEntity<List<ApplicationView>> buildApplicationsResponse(List<Application> applications) {

        var applicationViewList = applications.stream()
                .map(ApplicationConverter::buildApplicationView)
                .collect(Collectors.toList());

        return new ResponseEntity<>(applicationViewList, HttpStatus.OK);
    }

    private ResponseEntity<NewApplicationResponseView> buildNewApplicationResponse(ApplicationView applicationView) {

        var link =  "/configuration/applications/" + applicationView.getName();

        var href = new HrefView();
        href.setLink(link);

        var response = new NewApplicationResponseView();
        response.setApplication(applicationView);
        response.setHref(href);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
