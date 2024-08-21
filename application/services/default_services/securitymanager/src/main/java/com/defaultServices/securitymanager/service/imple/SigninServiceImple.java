package com.defaultServices.securitymanager.service.imple;

import com.defaultServices.securitymanager.Exception.BusinessException;
import com.defaultServices.securitymanager.Exception.ErrorCode;
import com.defaultServices.securitymanager.configuration.JwtService;
import com.defaultServices.securitymanager.dao.ConstentDao;
import com.defaultServices.securitymanager.dao.SigninDao;
import com.defaultServices.securitymanager.dto.*;
import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;
import com.defaultServices.securitymanager.service.SigninService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class SigninServiceImple implements SigninService {
    @Autowired
    SigninDao signinDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private ConstentDao constentDao;

    @Override
    public UserResponseDto signup(UserRequestDto signupRequestDto) throws BusinessException {
        log.info("Enter into signup:SigninServiceImple");
        Role role = signinDao.findByRole("User");
        signupRequestDto.setRole(List.of(role.getId()));
        signupRequestDto.setIdtoken("");
        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        String userEmail= signupRequestDto.getEmail();
        boolean emailExists = signinDao.existsByUserEmail(userEmail);

        if (emailExists) {
            log.info("Error from signup:SigninServiceImple ");
            throw new BusinessException(ErrorCode.DATA_ALREADY_EXISTS.code(),ErrorCode.DATA_ALREADY_EXISTS.message());
        } else {
            User saveUser = new User();
            BeanUtils.copyProperties(signupRequestDto,saveUser);
            User savedUser=signinDao.saveUser(saveUser);

            UserResponseDto userResponseDto= new UserResponseDto();
            BeanUtils.copyProperties(savedUser,userResponseDto);
            log.info("Exit from signup:SigninServiceImple ");
            return userResponseDto;
        }
    }

    @Override
    public ResponseEntity<UserResponseDto> login(UserRequestDto loginDetails) throws BusinessException {
        log.info("Enter into login:SigninServiceImple");
        User user = signinDao.findByEmail(loginDetails.getEmail());
        boolean password =passwordEncoder.matches(loginDetails.getPassword(), user.getPassword());

        if (password) {
            user.setLoggedinDate(new Date());
            User loginUser= signinDao.saveUser(user);
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(loginUser,userResponseDto);
            log.info("Exit from login:SigninServiceImple ");
            return ResponseEntity.ok(userResponseDto);
        } else {
            log.info("Error from login:SigninServiceImple ");
            throw new BusinessException(ErrorCode.INCORRECT_DATA.code(),ErrorCode.INCORRECT_DATA.message());
        }
    }

    @Override
    public ResponseEntity<UserResponseDto> logout(UserRequestDto requestDto) throws BusinessException {
        log.info("Enter into logout:SigninServiceImple");
        Optional<User> userOptional= signinDao.getUserById(requestDto.getId());
        if(userOptional.isPresent()){
            userOptional.get().setLoggedoutDate(new Date());
            User loginUser= signinDao.saveUser(userOptional.get());

            log.info(loginUser.getLoggedoutDate().toString());

            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(loginUser,userResponseDto);
            log.info("Exit from logout:SigninServiceImple ");
            return ResponseEntity.ok(userResponseDto);
        }else{
            log.info("Error from logout:SigninServiceImple ");
            throw new BusinessException(ErrorCode.NOT_FOUND.code(),ErrorCode.NOT_FOUND.message());
        }

    }
    @Override
    public ResponseEntity<String> googleService(UserRequestDto request) {
        /*boolean userRoleFound = signinDao.findAllRoles().stream()
                .anyMatch(roles -> "User".equals(roles.getRole())) ? true : false;

        if (userRoleFound) {

            String userName = jwtService.extractUserName(request.getIdtoken());
            Optional<User> tokenInfo = constentDao.findByUserId(userName);

            Users userObject = new Users();
            userObject.setFirstName(tokenInfo.get().getFirstname());
            userObject.setLastName(tokenInfo.get().getLastname());
            userObject.setUserName(tokenInfo.get().getUsername());
            userObject.setEmail(tokenInfo.get().getEmail());
            userObject.setRole(tokenInfo.get().getRole());

            signinDao.saveUser(tokenInfo.get());
            String idToken = jwtService.generateToken(userObject);
            return ResponseEntity.ok(idToken);

        }
        return ResponseEntity.ok("user Not found");*/
        return null;
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("Enter into getAllUsers:SigninServiceImple");
        List<User> userList=signinDao.getAllUsers();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user: userList){
            UserResponseDto userResponseDto= new UserResponseDto();
            BeanUtils.copyProperties(user,userResponseDto);
            userResponseDtoList.add(userResponseDto);
        }
        log.info("Exit from getAllUsers:SigninServiceImple ");
        return ResponseEntity.ok(userResponseDtoList);
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(String id) {
        log.info("Enter into getUserById:SigninServiceImple");
        Optional<User> user=signinDao.getUserById(id);
        UserResponseDto userResponseDto= new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        log.info("Exit from getUserById:SigninServiceImple ");
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto) {
        log.info("Enter into updateUser:SigninServiceImple");
        UserResponseDto updatedUserResponseDto = new UserResponseDto();

        if(signinDao.existsByUserId(userRequestDto.getId())) {
            User user = new User();
            List<String> roleList = new ArrayList<>();
            for(String reqRoleName : userRequestDto.getRole()){
                Role role = signinDao.findByRole(reqRoleName);
                roleList.add(role.getId());
            }
            BeanUtils.copyProperties(userRequestDto, user);
            user.setRole(roleList);
            User updatedUser = signinDao.updateUser(user);
            updatedUser.setRole(userRequestDto.getRole());
            BeanUtils.copyProperties(updatedUser, updatedUserResponseDto);
        }
        log.info("Exit from updateUser:SigninServiceImple ");
        return ResponseEntity.ok(updatedUserResponseDto);
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserById(UserRequestDto userRequestDto) {
        log.info("Enter into updateUserById:SigninServiceImple");
        UserResponseDto updatedUserResponseDto = new UserResponseDto();

        if(signinDao.existsByUserId(userRequestDto.getId())) {
            Optional<User> userOptional=signinDao.getUserById(userRequestDto.getId());
            userRequestDto.setRole(userOptional.get().getRole());
            User user= new User();
            BeanUtils.copyProperties(userRequestDto,user);
            User updatedUser = signinDao.updateUser(user);
            BeanUtils.copyProperties(updatedUser, updatedUserResponseDto);

        }
        log.info("Exit from updateUserById:SigninServiceImple ");
        return ResponseEntity.ok(updatedUserResponseDto);

    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserImage(UserRequestDto userRequestDto) {
        log.info("Enter into updateUserImage:SigninServiceImple");
        UserResponseDto userResponseDto = new UserResponseDto();
        if(signinDao.existsByUserId(userRequestDto.getId())){
           Optional<User> user = signinDao.getUserById(userRequestDto.getId());
           user.get().setRole(user.get().getRole());
           user.get().setAvatar(userRequestDto.getAvatar());
           signinDao.saveUser(user.get());
           BeanUtils.copyProperties(user,userResponseDto);
        }
        log.info("Exit from updateUserImage:SigninServiceImple ");
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public void deleteUser(String id) throws BusinessException {
        log.info("Enter into deleteUser:SigninServiceImple");
        boolean isExistingUser = signinDao.existsByUserId(id);
        if(isExistingUser){
            log.info("Exit from deleteUser:SigninServiceImple ");
            signinDao.deleteUser(id);
        }else{
            log.info("Error from deleteUser:SigninServiceImple ");
            throw new BusinessException(ErrorCode.NOT_FOUND.code(),ErrorCode.NOT_FOUND.message());
        }
    }

    @Override
    public void saveRolesFromJson(String filesPath) throws IOException {
        log.info("Enter into saveRolesFromJson:SigninServiceImple");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Role> newRoles = Arrays.asList(objectMapper.readValue(new File(filesPath), Role[].class));

        List<Role> existingRoles = signinDao.findAllRoles();

        for (Role newRole : newRoles) {
            boolean roleExists = existingRoles.stream()
                    .anyMatch(existingRole -> existingRole.getRole().equals(newRole.getRole()));

            if (roleExists) {
                Role existingRole = existingRoles.stream()
                        .filter(role -> role.getRole().equals(newRole.getRole()))
                        .findFirst()
                        .orElse(null);

                if (existingRole != null) {
                    existingRole.setRole(newRole.getRole());
                }
            } else {
                signinDao.save(newRole);
            }
        }
        existingRoles.stream()
                .filter(existingRole -> newRoles.stream().noneMatch(newRole -> newRole.getRole().equals(existingRole.getRole())))
                .forEach(signinDao::deleteRole);
        log.info("Exit from saveRolesFromJson:SigninServiceImple ");
    }


    @Override
    public ResponseEntity<RoleResponseDto> saveRole(RoleRequestDto requestDto) throws BusinessException{
        log.info("Enter into saveRole:SigninServiceImple");
        requestDto.setCreated_at(new Date());
        RoleResponseDto roleResponseDto= new RoleResponseDto();
        if(!(signinDao.existsByRole(requestDto.getRole()))){
            Role role =new Role();
            BeanUtils.copyProperties(requestDto,role);
            Role savedrole=signinDao.save(role);
            BeanUtils.copyProperties(savedrole,roleResponseDto);
            log.info("Exit from saveRole:SigninServiceImple ");
            return ResponseEntity.ok(roleResponseDto);
        }else {
            log.info("Error from saveRole:SigninServiceImple ");
            throw new BusinessException(ErrorCode.ROLE_ALREADY_EXISTS.code(),ErrorCode.ROLE_ALREADY_EXISTS.message());
        }

    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        log.info("Enter into getAllRoles:SigninServiceImple");
        List<Role> roleList=signinDao.getAllRoles();
        List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();

        for(Role roles: roleList){
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            BeanUtils.copyProperties(roles,roleResponseDto);
            roleResponseDtoList.add(roleResponseDto);
        }
        log.info("Exit from getAllRoles:SigninServiceImple ");
        return ResponseEntity.ok(roleResponseDtoList);
    }

    @Override
    public void deleteRole(String id) throws BusinessException {
        log.info("Enter into deleteRole:SigninServiceImple");
        boolean existsRole = signinDao.existsByRoleId(id);
        if(existsRole){
            signinDao.deleteRole(id);
            log.info("Exit from deleteRole:SigninServiceImple ");
        }else{
            log.info("Error from deleteRole:SigninServiceImple ");
            throw new BusinessException(ErrorCode.NOT_FOUND.code(),ErrorCode.NOT_FOUND.message());
        }
    }
}
