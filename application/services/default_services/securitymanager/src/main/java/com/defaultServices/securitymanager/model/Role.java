package com.defaultServices.securitymanager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "roles")
@Data
public class Role {

    @Id
    private String id;
    private String role;
    private Date created_at;
}

