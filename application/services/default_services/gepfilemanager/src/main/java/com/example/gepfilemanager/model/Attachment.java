package com.example.gepfilemanager.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Attachments")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;
    private String fileKey;
    private Object fileData;
    private String resource;
    private Date createdOn;
    private Date updatedOn;

}

