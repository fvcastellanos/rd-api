package com.dorefactor.deployer.repository;

import com.dorefactor.deployer.repository.configuration.BaseRepositoryITConfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    BaseRepositoryITConfig.class,
})
public abstract class BaseRepositoryIT {
}