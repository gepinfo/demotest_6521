package com.example.authproxy.controller;

import com.example.authproxy.dto.UserDetails;
import com.example.authproxy.service.ProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class ProxyController {

    private final ProxyService proxyService;

    @Autowired
    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping("/proxy")
    public ResponseEntity<Object> userController(@RequestBody UserDetails userDetails) {
        log.info("info", "Enter into ProxyController.java: userController");
        Object response = proxyService.userService(userDetails);

        log.info("info","Exit from ProxyController.java: userController");
        return ResponseEntity.ok(response);
    }
}

