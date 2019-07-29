package com.dorefactor.deployer.dao;

import java.util.List;
import java.util.Optional;

import com.dorefactor.deployer.dao.model.Application;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ApplicationMongoDao extends BaseDao implements ApplicationDao {

    public ApplicationMongoDao(MongoOperations mongoOperations) {

        super(mongoOperations);
    }

    @Override
    public List<Application> getAll() {

        return mongoTemplate.findAll(Application.class);
    }

    @Override
    public Optional<Application> getByName(String name) {

        var query = Query.query(Criteria.where("name").is(name));
        var result = mongoTemplate.findOne(query, Application.class);

        return Optional.ofNullable(result);
    }

    @Override
    public Application save(Application application) {

        return mongoTemplate.save(application);
	}
}