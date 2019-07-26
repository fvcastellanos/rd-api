package com.dorefactor.deployer.repository;

import com.dorefactor.deployer.repository.configuration.BaseRepositoryITConfig;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;



// @DataMongoTest
// @BootstrapWith(DataMongoTestContextBootstrapper.class)
@RunWith(SpringRunner.class)
// @ExtendWith(SpringExtension.class)
// @TypeExcludeFilters(DataMongoTypeExcludeFilter.class)
@AutoConfigureDataMongo
@ContextConfiguration(classes = {
    BaseRepositoryITConfig.class,
})
public abstract class BaseRepositoryIT {
}