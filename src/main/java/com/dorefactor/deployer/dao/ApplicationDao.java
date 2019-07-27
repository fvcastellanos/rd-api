package com.dorefactor.deployer.dao;

import java.util.List;
import java.util.Optional;

import com.dorefactor.deployer.dao.model.Application;

public interface ApplicationDao {

    List<Application> getAll();
    Optional<Application> getByName(String name);
    Application save(Application application);
}