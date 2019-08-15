package com.dorefactor.deployer.message;

import com.dorefactor.deployer.configuration.ProducerConfigIT;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProducerConfigIT.class)
public abstract class BaseProducerIT {
}
