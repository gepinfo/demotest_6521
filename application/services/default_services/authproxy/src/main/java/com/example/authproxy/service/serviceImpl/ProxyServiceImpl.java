package com.example.authproxy.service.serviceImpl;

import com.example.authproxy.dao.ProxyDao;
import com.example.authproxy.dto.UserDetails;
import com.example.authproxy.service.ProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProxyServiceImpl implements ProxyService {

    private final ProxyDao proxyDao;

    @Autowired
    public ProxyServiceImpl(ProxyDao proxyDao) {
        this.proxyDao = proxyDao;

    }

    public Object userService(UserDetails userDetails) {
        log.info("info", "Enter into ProxyService.java: userService");
        Object response = proxyDao.userDao(userDetails);

        log.info("info", "Exit from ProxyService.java: userService");
        return response;
    }
}

