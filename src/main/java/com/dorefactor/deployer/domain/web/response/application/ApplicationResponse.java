package com.dorefactor.deployer.domain.web.response.application;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.web.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationResponse extends BaseResponse {

    private List<Application> applications;
}
