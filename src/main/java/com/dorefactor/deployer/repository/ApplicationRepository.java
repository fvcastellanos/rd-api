package com.dorefactor.deployer.repository;

import java.util.List;

import com.dorefactor.deployer.repository.model.Application;

import org.springframework.data.mongodb.core.MongoTemplate;

public class ApplicationRepository {

    private final MongoTemplate mongoTemplate;

    public ApplicationRepository(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    public List<Application> getAll() {

        return mongoTemplate.findAll(Application.class);
    }

}