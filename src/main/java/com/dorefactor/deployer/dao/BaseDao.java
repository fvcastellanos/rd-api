package com.dorefactor.deployer.dao;

import org.springframework.data.mongodb.core.MongoOperations;

public abstract class BaseDao {

    protected MongoOperations mongoTemplate;

    protected BaseDao(MongoOperations mongoOperations) {

        this.mongoTemplate = mongoOperations;
    }
}