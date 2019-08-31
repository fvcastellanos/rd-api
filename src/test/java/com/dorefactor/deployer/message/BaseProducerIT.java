package com.dorefactor.deployer.message;

import com.dorefactor.deployer.config.ProducerConfigIT;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {
        ProducerConfigIT.class
})
abstract class BaseProducerIT {
}
