package com.default_service.gcam.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "screens")
@Data
public class Screen{

    @Id
    private String id;

    private String resources;
    private String role;
    private Date created_at;

}

