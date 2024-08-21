package com.example.authproxy.service;

import com.example.authproxy.dto.UserDetails;

public interface ProxyService {

    Object userService(UserDetails userDetails);
}
