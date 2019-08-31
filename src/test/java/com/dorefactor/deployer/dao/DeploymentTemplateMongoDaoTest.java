package com.dorefactor.deployer.dao;

import com.dorefactor.deployer.domain.model.DeploymentTemplate;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DeploymentTemplateMongoDaoTest extends BaseDaoIT {

    @Autowired
    private DeploymentTemplateDao deploymentTemplateDao;

    @Test
    void testGetAll() {

        var templates = Lists.newArrayList(saveRandomDeploymentTemplate(), saveRandomDeploymentTemplate());

        var list = deploymentTemplateDao.getAll();

        assertThat(list).containsAll(templates);
    }

    @Test
    void testSave() {

        var deploymentTemplate = ModelFixture.buildDeploymentTemplate();
        var storedTemplate = deploymentTemplateDao.save(deploymentTemplate);
        var expectedTemplate = getById(storedTemplate.getId());

        assertThat(storedTemplate).isEqualTo(expectedTemplate);
    }

    @Test
    void testGetByName() {

        var storedTemplate = saveRandomDeploymentTemplate();
        var expectedTemplate = deploymentTemplateDao.getByName(storedTemplate.getName());

        assertThat(expectedTemplate).get()
                                    .isEqualTo(storedTemplate);
    }

    @Test
    void testGetByNameNotFound() {

        var template = deploymentTemplateDao.getByName("something");

        assertThat(template).isEmpty();
    }

    @Test
    void testGetById() {

        var storedTemplate = saveRandomDeploymentTemplate();
        var expectedTemplate = deploymentTemplateDao.getById(storedTemplate.getId());

        assertThat(expectedTemplate).get()
                                    .isEqualTo(storedTemplate);
    }

    @Test
    void testGetByIdNotFound() {

        var id = ModelFixture.buildRandomObjectId();
        var template = deploymentTemplateDao.getById(id);

        assertThat(template).isEmpty();
    }

    // --------------------------------------------------------------------------------

    private DeploymentTemplate saveRandomDeploymentTemplate() {

        var template = ModelFixture.buildDeploymentTemplate();

        return mongoTemplate.save(template);
    }

    private DeploymentTemplate getById(ObjectId id) {

        return mongoTemplate.findById(id, DeploymentTemplate.class);
    }
}
