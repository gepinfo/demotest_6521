package com.default_service.gcam.Exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;
    private int errorCode;
}
