package com.dorefactor.deployer.config;

import com.dorefactor.deployer.web.controller.configuration.AbstractConfigurationController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        AbstractConfigurationController.class
})
public class ControllerConfig {
}
