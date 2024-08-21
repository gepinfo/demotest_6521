package com.defaultServices.securitymanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleResponseDto {
    String id;
    String role;
    Date created_at;
}
