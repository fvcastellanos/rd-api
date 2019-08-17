package com.dorefactor.deployer.domain.web.view.application;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.domain.web.view.BaseResponseView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationResponseView extends BaseResponseView {

    private List<Application> applications;
}
