package com.dorefactor.deployer.domain.model.docker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Image {

    private String name;
    private String tag;
}