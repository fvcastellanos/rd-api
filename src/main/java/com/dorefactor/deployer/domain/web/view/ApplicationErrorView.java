package com.dorefactor.deployer.domain.web.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ApplicationErrorView extends BaseResponseView {

    private String message;
}
