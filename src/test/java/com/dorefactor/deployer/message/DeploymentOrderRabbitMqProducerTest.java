package com.dorefactor.deployer.message;

import com.dorefactor.deployer.fixture.listener.RabbitListenerFixture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class DeploymentOrderRabbitMqProducerTest extends BaseProducerIT {

    @Autowired
    private DeploymentOrderProducer deploymentOrderProducer;

    @Autowired
    private RabbitListenerFixture rabbitListenerFixture;

    @Test
    public void testDeploymentOrderRequestsAreQueued() {

        String message = "test message";
        deploymentOrderProducer.produce(message);

        await().atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(rabbitListenerFixture.getLastMessage()).isEqualTo(message));
    }
}
