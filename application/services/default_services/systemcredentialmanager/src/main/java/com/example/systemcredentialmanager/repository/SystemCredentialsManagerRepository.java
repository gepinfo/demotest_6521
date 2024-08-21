package com.example.systemcredentialmanager.repository;


import com.example.systemcredentialmanager.model.SystemCredentialsExternalManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemCredentialsManagerRepository extends MongoRepository<SystemCredentialsExternalManager,String> {
    SystemCredentialsExternalManager findByUserId(String userId);
    void deleteByUserId(String userId);
}
