package com.defaultServices.securitymanager.controller;

import com.defaultServices.securitymanager.dto.ConsentRequestDto;
import com.defaultServices.securitymanager.dto.UserResponseDto;
import com.defaultServices.securitymanager.service.ConsentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(origins = "*")
public class ConsentController {

    @Autowired
    ConsentService consentService;

    @PutMapping("consent")
    public ResponseEntity<UserResponseDto> consent(@RequestBody ConsentRequestDto consentRequest) {
        log.info("Enter into consent:ConsentController");
        return consentService.consent(consentRequest);
    }
}

