package com.geppetto.demotest.dao.daoimpl;

import com.geppetto.demotest.model.User;
import com.geppetto.demotest.repository.UserRepository;
import com.geppetto.demotest.dao.UserDao;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao{

    private final UserRepository userRepository;

     public UserDaoImpl(UserRepository userRepository) {
       this.userRepository = userRepository;
}





}
