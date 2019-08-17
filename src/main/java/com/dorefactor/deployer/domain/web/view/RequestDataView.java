package com.dorefactor.deployer.domain.web.view;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RequestDataView {

    private String id;
    private Instant time;
}
