package com.defaultServices.securitymanager.dao.imple;

import com.defaultServices.securitymanager.dao.SigninDao;
import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;
import com.defaultServices.securitymanager.repositories.RoleRepository;
import com.defaultServices.securitymanager.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class SigninDaoImple implements SigninDao {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUserId(String id) {
        log.info("Enter into existsByUserId:SigninDaoImple");
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUserEmail(String email) {
        log.info("Enter into existsByUserEmail:SigninDaoImple");
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        log.info("Enter into findByEmailAndPassword:SigninDaoImple");
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Enter into getAllUsers:SigninDaoImple");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        log.info("Enter into getUserById:SigninDaoImple");
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        log.info("Enter into updateUser:SigninDaoImple");
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        log.info("Enter into deleteUser:SigninDaoImple");
        userRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Role> roles) {
        log.info("Enter into saveAll:SigninDaoImple");
        roleRepository.saveAll(roles);
    }

    @Override
    public Role save(Role requestDto) {
        log.info("Enter into save:SigninDaoImple");
        return roleRepository.save(requestDto);
    }

    @Override
    public boolean existsByRoleId(String id) {
        log.info("Enter into existsByRoleId:SigninDaoImple");
        return roleRepository.existsById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        log.info("Enter into getAllRoles:SigninDaoImple");
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(String id) {
        log.info("Enter into deleteRole:SigninDaoImple");
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRole(String role) {
        log.info("Enter into findByRole:SigninDaoImple");
        return roleRepository.findByRole(role);
    }

    @Override
    public User findByEmail(String email) {
        log.info("Enter into findByEmail:SigninDaoImple");
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByRole(String role) {
        log.info("Enter into existsByRole:SigninDaoImple");
        return roleRepository.existsByRole(role);
    }

    public List<Role> findAllRoles() {
        log.info("Enter into findAllRoles:SigninDaoImple");
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Role role) {
        log.info("Enter into deleteRole:SigninDaoImple");
        roleRepository.delete(role);
    }


}
