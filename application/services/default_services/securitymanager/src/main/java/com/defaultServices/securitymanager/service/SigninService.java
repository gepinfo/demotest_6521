package com.defaultServices.securitymanager.service;

import com.defaultServices.securitymanager.dto.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface SigninService {

    UserResponseDto signup(UserRequestDto signupRequestDto);

    ResponseEntity<UserResponseDto> login(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> logout(UserRequestDto requestDto);

    ResponseEntity<String> googleService(UserRequestDto requestDto);

    ResponseEntity<List<UserResponseDto>> getAllUsers();

    ResponseEntity<UserResponseDto> getUserById(String id);

    ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> updateUserById(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> updateUserImage(UserRequestDto userRequestDto);

    void deleteUser(String id);

    void saveRolesFromJson(String filePath) throws IOException;

    ResponseEntity<RoleResponseDto> saveRole(RoleRequestDto requestDto);

    ResponseEntity<List<RoleResponseDto>> getAllRoles();

    void deleteRole(String id);

}
