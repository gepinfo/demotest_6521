package com.example.authproxy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetails {

        private String firstname;
        private String lastname;
        private List<String> listOfRoles;
        private String id;
        private String username;
        private String email;
}
