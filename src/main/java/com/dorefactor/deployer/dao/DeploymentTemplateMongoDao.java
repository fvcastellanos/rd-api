package com.dorefactor.deployer.dao;

import java.util.List;
import java.util.Optional;

import com.dorefactor.deployer.dao.model.DeploymentTemplate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;

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
        return null;
    }

    @Override
    public Optional<DeploymentTemplate> getByName(String name) {
		return null;
	}

	@Override
	public Optional<DeploymentTemplate> getById(ObjectId id) {
		return null;
	}


}