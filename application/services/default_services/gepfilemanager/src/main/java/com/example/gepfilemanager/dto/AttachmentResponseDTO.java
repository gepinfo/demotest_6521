package com.example.gepfilemanager.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponseDTO {
    private String fileUrl;
    private String fileKey;
    private Object fileData;
    private String resource;
    private Date createdOn;
    private Date updatedOn;
}
