package com.dorefactor.deployer.domain.web.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringStyle;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Getter
@Setter
public class BaseResponse {

    private RequestData request;

    @Override
    public String toString() {
        return reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
