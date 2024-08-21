package com.default_service.gcam.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "resources")
@Data
public class Resource {

    @Id
    private String id;

    private String resource_name;
    private String resource_type;
    private List<String> roles;
    private List<Object> components;
    private Date created_at;

    public Resource() {
        this.id = UUID.randomUUID().toString();
        this.created_at = new Date();
    }

}
