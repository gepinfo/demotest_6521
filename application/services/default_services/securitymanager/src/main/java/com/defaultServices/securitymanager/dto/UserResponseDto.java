package com.defaultServices.securitymanager.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDto {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String signintype;
    private String fbId;
    private String githubId;
    private String phonenumber;
    private String avatar;
    private String Idtoken;
    private Date loggedinDate;
    private Date loggedoutDate;
    private List<String> role;
    private String org;
    private String org_country;
    private String org_sub1;
    private String org_sub2;
    private String org_sub3;
}
