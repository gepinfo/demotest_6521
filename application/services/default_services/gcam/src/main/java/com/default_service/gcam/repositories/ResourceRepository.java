package com.default_service.gcam.repositories;

import com.default_service.gcam.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {
    List<Resource> findByRolesIn(List<String> role);
}

