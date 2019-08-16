package com.dorefactor.deployer.domain.web.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RequestData {

    private String id;
    private Instant time;
}
