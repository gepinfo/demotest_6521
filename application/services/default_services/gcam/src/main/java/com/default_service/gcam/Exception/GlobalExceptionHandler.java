package com.default_service.gcam.Exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    ExceptionResponse incorrectData(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        ExceptionResponse exceptionResponse= new ExceptionResponse();
        exceptionResponse.setErrorCode(ex.getErrorCode());
        exceptionResponse.setMessage(ex.getMessage());
        return exceptionResponse;
    }

}
