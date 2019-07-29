package com.dorefactor.deployer.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeploymentTemplateMongoDaoTest extends BaseDaoIT {
    
    @Autowired
    private DeploymentTemplateDao deploymentTemplateDao;

    @Test
    public void testGetAll() {

        var templates = Lists.newArrayList(saveRandomDeploymentTemplate(), saveRandomDeploymentTemplate());

        var list = deploymentTemplateDao.getAll();

        assertThat(list).containsAll(templates);
    }

    @Test
    public void testSave() {

        var deploymentTemplate = ModelFixture.builDeploymentTemplate();
        var storedTemplate = deploymentTemplateDao.save(deploymentTemplate);
        var expectedTemplate = getById(storedTemplate.getId());

        assertThat(storedTemplate).isEqualTo(expectedTemplate);
    }

    @Test
    public void testGetByName() {

        var storedTemplate = saveRandomDeploymentTemplate();
        var expectedTemplate = deploymentTemplateDao.getByName(storedTemplate.getName());

        assertThat(expectedTemplate).get()
            .isEqualTo(storedTemplate);
    }

    @Test
    public void testGetById() {

        var storedTemplate = saveRandomDeploymentTemplate();
        var expectedTemplate = deploymentTemplateDao.getById(storedTemplate.getId());

        assertThat(expectedTemplate).get()
            .isEqualTo(storedTemplate);
    }
    // --------------------------------------------------------------------------------

    private DeploymentTemplate saveRandomDeploymentTemplate() {

        var template = ModelFixture.builDeploymentTemplate();

        return mongoTemplate.save(template);        
    }

    private DeploymentTemplate getById(ObjectId id) {

        return mongoTemplate.findById(id, DeploymentTemplate.class);
    }
}