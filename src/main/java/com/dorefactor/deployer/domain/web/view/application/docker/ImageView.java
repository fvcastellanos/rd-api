package com.dorefactor.deployer.domain.web.view.application.docker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ImageView {

    private String name;
    private String tag;
}
