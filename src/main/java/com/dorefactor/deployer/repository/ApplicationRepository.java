package com.dorefactor.deployer.repository;

import java.util.List;

import com.dorefactor.deployer.repository.model.Application;

import org.springframework.data.mongodb.core.MongoOperations;

public class ApplicationRepository {

    private final MongoOperations mongoTemplate;

    public ApplicationRepository(MongoOperations mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    public List<Application> getAll() {

        return mongoTemplate.findAll(Application.class);
    }

}