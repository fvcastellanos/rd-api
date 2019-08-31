package com.dorefactor.deployer.dao;

import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationMongoDaoTest extends BaseDaoIT {

    @Autowired
    private ApplicationMongoDao applicationDao;

    @Test
    void testGetAll() {

        var applications = Lists.newArrayList(saveRandomApplication(), saveRandomApplication());

        var apps = applicationDao.getAll();

        assertThat(apps).containsAll(applications);
    }

    @Test
    void testGetByNameWithExistingApp() {

        var application = saveRandomApplication();

        var appHolder = applicationDao.getByName(application.getName());

        assertThat(appHolder).get()
                             .isEqualTo(application);
    }

    @Test
    void testGetByNameNonExistingApp() {

        var appHolder = applicationDao.getByName("not-existing-app");

        assertThat(appHolder).isEmpty();
    }

    @Test
    void testSave() {

        var application = ModelFixture.buildDockerApplication();

        var app = applicationDao.save(application);

        var storedApp = getApplicationById(app.getId());

        assertThat(app).isEqualTo(storedApp);
    }

    // ------------------------------------------------------------------------------------

    private Application saveRandomApplication() {

        return mongoTemplate.save(ModelFixture.buildDockerApplication());
    }

    private Application getApplicationById(ObjectId id) {

        return mongoTemplate.findById(id, Application.class);
    }
}
