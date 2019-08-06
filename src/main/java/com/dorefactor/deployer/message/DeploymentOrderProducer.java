package com.dorefactor.deployer.message;

public interface DeploymentOrderProducer {

    void produce(String message);
}