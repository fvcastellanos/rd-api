package com.dorefactor.deployer.domain.web.view.application;

import com.dorefactor.deployer.domain.web.view.BaseResponseView;
import com.dorefactor.deployer.domain.web.view.HrefView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewApplicationResponseView extends BaseResponseView {

    private ApplicationView application;
    private HrefView href;
}
