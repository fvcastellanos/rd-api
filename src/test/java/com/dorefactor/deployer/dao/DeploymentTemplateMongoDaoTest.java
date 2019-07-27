package com.dorefactor.deployer.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.google.common.collect.Lists;

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

    // --------------------------------------------------------------------------------

    private DeploymentTemplate saveRandomDeploymentTemplate() {

        var template = ModelFixture.builDeploymentTemplate();

        return mongoTemplate.save(template);        
    }
}