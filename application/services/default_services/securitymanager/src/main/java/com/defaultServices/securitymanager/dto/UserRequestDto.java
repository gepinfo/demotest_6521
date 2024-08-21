package com.defaultServices.securitymanager.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserRequestDto {
    String id;
    String firstname;
    String lastname;
    String username;
    String email;
    String password;
    String avatar;
    String Idtoken;
    List<String> role;
    String signintype;
    String fbId;
    String githubId;
    String phonenumber;
    Date loggedinDate;
    Date loggedoutDate;
    String org;
    String org_country;
    String org_sub1;
    String org_sub2;
    String org_sub3;
}
