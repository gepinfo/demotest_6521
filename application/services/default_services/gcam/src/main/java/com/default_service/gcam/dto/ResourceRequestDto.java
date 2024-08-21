package com.default_service.gcam.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResourceRequestDto {
    private String id;
    private String resource_name;
    private String resource_type;
    private List<String> roles;
    private List<Object> components;
    private Date created_at;
}
