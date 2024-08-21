package com.defaultServices.securitymanager.service;

import com.defaultServices.securitymanager.dto.ConsentRequestDto;
import com.defaultServices.securitymanager.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface ConsentService {

    ResponseEntity<UserResponseDto> consent(ConsentRequestDto consentRequestDto);
}
