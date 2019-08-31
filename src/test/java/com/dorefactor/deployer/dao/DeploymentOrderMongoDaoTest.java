package com.dorefactor.deployer.dao;

import com.dorefactor.deployer.domain.model.DeploymentOrder;
import com.dorefactor.deployer.fixture.ModelFixture;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DeploymentOrderMongoDaoTest extends BaseDaoIT {

    @Autowired
    private DeploymentOrderDao deploymentOrderDao;

    @Test
    void testSave() {

        var order = ModelFixture.buildDeploymentOrder();
        var storedOrder = deploymentOrderDao.save(order);
        var expectedOrder = getById(storedOrder.getId());

        assertThat(storedOrder).isEqualTo(expectedOrder);
    }

    @Test
    void testGetByRequestId() {

        var order = saveRandomDeploymentOrder();

        var storedOrder = deploymentOrderDao.getByRequestId(order.getRequestId());

        assertThat(storedOrder).get()
                               .isEqualTo(order);
    }

    @Test
    void testGetByNonExistingRequestId() {

        var order = deploymentOrderDao.getByRequestId("not-existing-request-id");

        assertThat(order).isEmpty();
    }

    // ----------------------------------------------------------------------------

    private DeploymentOrder getById(ObjectId id) {

        return mongoTemplate.findById(id, DeploymentOrder.class);
    }

    private DeploymentOrder saveRandomDeploymentOrder() {

        return mongoTemplate.save(ModelFixture.buildDeploymentOrder());
    }
}
