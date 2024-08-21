package com.defaultServices.securitymanager.dao;

import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;

import java.util.Optional;

public interface ConstentDao {

    Optional<User> findByUserId(String id);

    Optional<Role> findByRoleId(String role);

    void saveUser(User user);
}
