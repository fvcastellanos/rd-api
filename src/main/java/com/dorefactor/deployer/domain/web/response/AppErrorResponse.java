package com.dorefactor.deployer.domain.web.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AppErrorResponse extends BaseResponse {

    private String message;
}
