package com.example.authproxy.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {

    private List<String> listOfRoles;
}
