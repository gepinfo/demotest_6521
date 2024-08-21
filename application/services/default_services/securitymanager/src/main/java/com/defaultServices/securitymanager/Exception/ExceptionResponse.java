package com.defaultServices.securitymanager.Exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;
    private int errorCode;
}
