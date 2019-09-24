package com.dorefactor.deployer.message;

import com.dorefactor.deployer.fixture.listener.RabbitListenerFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class DeploymentOrderRabbitMqProducerTest extends BaseProducerIT {

    @Autowired
    private DeploymentOrderProducer deploymentOrderProducer;

    @Test
    void testDeploymentOrderRequestsAreQueued() {

        String message = "test message";
        deploymentOrderProducer.produce(message);

        await().atMost(5, TimeUnit.SECONDS)
               .untilAsserted(() -> assertThat(RabbitListenerFixture.getLastMessage()).isEqualTo(message));
    }
}
