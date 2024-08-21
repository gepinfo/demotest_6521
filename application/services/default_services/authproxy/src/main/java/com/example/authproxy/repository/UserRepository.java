package com.example.authproxy.repository;

import com.example.authproxy.model.SignIn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<SignIn,String> {
}
