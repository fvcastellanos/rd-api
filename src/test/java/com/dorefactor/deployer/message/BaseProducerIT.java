package com.dorefactor.deployer.message;

import com.dorefactor.deployer.config.ProducerConfigIT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest
@SpringJUnitConfig(classes = {
        ProducerConfigIT.class
})
abstract class BaseProducerIT {
}
