package com.defaultServices.securitymanager.repositories;

import com.defaultServices.securitymanager.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {
    Role findByRole(String user);
    boolean existsByRole(String role);
}
