package com.dorefactor.deployer.domain.web.view;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringStyle;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Getter
@Setter
public class BaseResponseView {

    @Override
    public String toString() {
        return reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
