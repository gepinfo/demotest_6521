package com.example.systemcredentialmanager.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemCredentialExtUpdateDto {

    private String role;
    private String userId;
    private List<Object> credentials;
    private String modifiedBy;
}
