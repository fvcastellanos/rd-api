package com.dorefactor.deployer.dao;

import java.util.Optional;

import com.dorefactor.deployer.domain.model.DeploymentOrder;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DeploymentOrderMongoDao extends BaseDao implements DeploymentOrderDao {

    public DeploymentOrderMongoDao(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

	@Override
    public DeploymentOrder save(DeploymentOrder deploymentOrder) {

        return mongoTemplate.save(deploymentOrder);
    }

	@Override
	public Optional<DeploymentOrder> getByRequestId(String requestId) {

        var query = Query.query(Criteria.where("requestId").is(requestId));
        var result = mongoTemplate.findOne(query, DeploymentOrder.class);

        return Optional.ofNullable(result);
	}
}