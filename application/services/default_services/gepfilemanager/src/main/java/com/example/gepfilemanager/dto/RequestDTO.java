package com.example.gepfilemanager.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String fileUrl;
    private String fileKey;
    private String resource;
}
