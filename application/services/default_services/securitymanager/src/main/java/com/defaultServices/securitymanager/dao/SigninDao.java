package com.defaultServices.securitymanager.dao;

import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;

import java.util.List;
import java.util.Optional;

public interface SigninDao {

    User saveUser(User user);

    boolean existsByUserId(String id);

    boolean existsByUserEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User updateUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    void deleteUser(String id);

    void saveAll(List<Role> roles);

    Role save(Role requestDto);

    boolean existsByRoleId(String id);

    List<Role> getAllRoles();

    void deleteRole(String id);

    Role findByRole(String user);

    User findByEmail(String email);

    boolean existsByRole(String role);

    public List<Role> findAllRoles();

    void deleteRole(Role role);
}
