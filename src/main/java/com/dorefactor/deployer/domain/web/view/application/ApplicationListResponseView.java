package com.dorefactor.deployer.domain.web.view.application;

import com.dorefactor.deployer.domain.web.view.BaseResponseView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationListResponseView extends BaseResponseView {

    private List<ApplicationView> applications;
}
