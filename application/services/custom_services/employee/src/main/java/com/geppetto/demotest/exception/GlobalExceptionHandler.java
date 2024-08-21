package com.geppetto.demotest.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

@ExceptionHandler(EntityNotFoundException.class)
public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error("Entity not found exception caught", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    }