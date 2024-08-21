package com.defaultServices.securitymanager.Exception;

public enum ErrorCode {
    DATA_ALREADY_EXISTS("Email is already exists", 102),
    ROLE_ALREADY_EXISTS("Role is already exists", 103),
    INVALID_DATA("Incorrect User Data", 106),
    INCORRECT_DATA("Incorrect Username or Password", 105),
    EXPIRED("Invalid or expired refresh token",107),
    NOT_FOUND("value not found", 404);


    private int errorCode;
    private String errorMessage;

    private ErrorCode(String message, int value) {
        this.errorCode = value;
        this.errorMessage = message;
    }

    public int code() {
        return errorCode;
    }

    public String message() {
        return errorMessage;
    }

}
