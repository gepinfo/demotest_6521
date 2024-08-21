package com.default_service.gcam.repositories;

import com.default_service.gcam.model.Screen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends MongoRepository<Screen, String> {
}
