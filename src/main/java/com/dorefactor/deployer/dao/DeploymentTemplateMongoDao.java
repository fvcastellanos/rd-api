package com.dorefactor.deployer.dao;

import java.util.List;
import java.util.Optional;

import com.dorefactor.deployer.domain.model.DeploymentTemplate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DeploymentTemplateMongoDao extends BaseDao implements DeploymentTemplateDao {

    public DeploymentTemplateMongoDao(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

	@Override
    public List<DeploymentTemplate> getAll() {

        return mongoTemplate.findAll(DeploymentTemplate.class);
    }

    @Override
    public DeploymentTemplate save(DeploymentTemplate deploymentTemplate) {

        return mongoTemplate.save(deploymentTemplate);
    }

    @Override
    public Optional<DeploymentTemplate> getByName(String name) {

        var query = Query.query(Criteria.where("name").is(name));
        var deploymentTemplate = mongoTemplate.findOne(query, DeploymentTemplate.class);
		return Optional.ofNullable(deploymentTemplate);
	}

	@Override
	public Optional<DeploymentTemplate> getById(ObjectId id) {
        
        var deploymentTemplate = mongoTemplate.findById(id, DeploymentTemplate.class);
		return Optional.ofNullable(deploymentTemplate);
	}
}