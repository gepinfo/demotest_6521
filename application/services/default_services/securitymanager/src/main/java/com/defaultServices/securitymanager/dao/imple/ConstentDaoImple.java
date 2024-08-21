package com.defaultServices.securitymanager.dao.imple;

import com.defaultServices.securitymanager.dao.ConstentDao;
import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;
import com.defaultServices.securitymanager.repositories.RoleRepository;
import com.defaultServices.securitymanager.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ConstentDaoImple implements ConstentDao {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByUserId(String id) {
        log.info("Enter into findByUserId:ConstentDaoImple");
        return userRepository.findById(id);
    }

    @Override
    public Optional<Role> findByRoleId(String id) {
        log.info("Enter into findByRoleId:ConstentDaoImple");
        return roleRepository.findById(id);
    }

    @Override
    public void saveUser(User user) {
        log.info("Enter into saveUser:ConstentDaoImple");
        userRepository.save(user);
    }
}
