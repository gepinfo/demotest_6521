package com.defaultServices.securitymanager.service.imple;

import com.defaultServices.securitymanager.Exception.BusinessException;
import com.defaultServices.securitymanager.Exception.ErrorCode;
import com.defaultServices.securitymanager.configuration.JwtService;
import com.defaultServices.securitymanager.dao.ConstentDao;
import com.defaultServices.securitymanager.dto.ConsentRequestDto;
import com.defaultServices.securitymanager.dto.UserResponseDto;
import com.defaultServices.securitymanager.model.Role;
import com.defaultServices.securitymanager.model.User;
import com.defaultServices.securitymanager.model.Users;
import com.defaultServices.securitymanager.service.ConsentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ConsentServiceImple implements ConsentService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ConstentDao consentDao;

    @Override
    public ResponseEntity<UserResponseDto> consent(ConsentRequestDto consentRequest) {
        log.info("Enter into consent:ConsentServiceImple ");
        Optional<User> user = consentDao.findByUserId(consentRequest.getId());
        UserResponseDto userResponseDto= new UserResponseDto();
        if (user != null && consentRequest.getScope().equalsIgnoreCase("openid") &&
                consentRequest.getSubmit().equalsIgnoreCase("Allow access")) {
            List<String> roleTypes = new ArrayList<>();
            for(String roleId : user.get().getRole() ){
                Optional<Role> role = consentDao.findByRoleId(roleId);
                String roleType= role.get().getRole();
                roleTypes.add(roleType);
            }

            var users= Users.builder()
                    .firstName(user.get().getFirstname())
                    .lastName(user.get().getLastname())
                    .email(user.get().getEmail())
                    .userName(user.get().getUsername())
                    .id(user.get().getId())
                    .role(roleTypes)
                    .build();

            if (roleTypes != null && roleTypes.size() != 0) {
                var token = jwtService.generateAccessToken(users);
                user.get().setIdtoken(token);
                consentDao.saveUser(user.get());

                BeanUtils.copyProperties(user.get(),userResponseDto);
                userResponseDto.setRole(roleTypes);
                log.info("Exit from consent:ConsentServiceImple");
                return ResponseEntity.ok(userResponseDto);
            } else {
                log.info("Error from consent:ConsentServiceImple");
                throw new BusinessException(ErrorCode.INVALID_DATA.code(),ErrorCode.INVALID_DATA.message());
            }
        } else {
            log.info("Error from consent:ConsentServiceImple");
            throw new BusinessException(ErrorCode.INVALID_DATA.code(),ErrorCode.INVALID_DATA.message());
        }
    }
}
