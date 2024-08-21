package com.default_service.gcam.Exception;

public enum ErrorCode {

    INCORRECT_DATA("Error reading or processing JSON files",500);

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
