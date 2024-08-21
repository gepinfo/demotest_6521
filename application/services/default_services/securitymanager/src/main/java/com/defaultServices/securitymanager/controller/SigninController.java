package com.defaultServices.securitymanager.controller;

import com.defaultServices.securitymanager.dto.*;
import com.defaultServices.securitymanager.service.SigninService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@Slf4j
public class SigninController {

    @Autowired
    SigninService signinService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto signupRequestDto) {
        log.info("Enter into signup:SigninController");
        UserResponseDto userResponseDto = signinService.signup(signupRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
    @PutMapping("/logout")
    public ResponseEntity<UserResponseDto> logout(@RequestBody UserRequestDto requestDto) {
        log.info("Enter into logout:SecurityManager");
        return signinService.logout(requestDto);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto) {
        log.info("Enter into login:SigninController");
        return signinService.login(userRequestDto);
    }



    @PostMapping("/googlesignin")
    public ResponseEntity<String> googleController(@RequestBody UserRequestDto requestDto) {
        log.info("Enter into googleController:SigninController");
        return signinService.googleService(requestDto);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("Enter into getAllUsers:SigninController");
        return signinService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        log.info("Enter into getUserById:SigninController");
        return signinService.getUserById(id);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Enter into updateUser:SigninController");
        return signinService.updateUser(userRequestDto);
    }

    @PutMapping("/updateuserid")
    public ResponseEntity<UserResponseDto> updateUserById(@RequestBody UserRequestDto userRequestDto) {
        log.info("Enter into updateUserById:SigninController");
        return signinService.updateUserById(userRequestDto);
    }

    @PutMapping("/updateuserimg")
    public ResponseEntity<UserResponseDto> updateuserimg(@RequestBody UserRequestDto userRequestDto) {
        log.info("Enter into updateuserimg:SigninController");
        return signinService.updateUserImage(userRequestDto);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable String id) {
        log.info("Enter into deleteUser:SigninController");
        signinService.deleteUser(id);
    }

    @PostMapping("/saveroles")
    public ResponseEntity<RoleResponseDto> saveRole(@RequestBody RoleRequestDto requestDto) {
        log.info("Enter into saveRole:SigninController");
        return signinService.saveRole(requestDto);
    }

    @GetMapping("/getallroles")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        log.info("Enter into getAllRoles:SigninController");
        return signinService.getAllRoles();
    }

    @DeleteMapping("/deleteroles/{id}")
    public void deleterole(@PathVariable String id) {
        log.info("Enter into deleterole:SigninController");
        signinService.deleteRole(id);
    }

}









