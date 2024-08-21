package com.defaultServices.securitymanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleRequestDto {
   String role;
   Date created_at;
}
