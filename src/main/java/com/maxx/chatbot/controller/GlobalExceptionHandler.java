package com.maxx.chatbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception details here if needed
        // System.err.println(ex.getMessage());
        
        return new ResponseEntity<>(
            "I'm currently experiencing high traffic or a temporary issue connecting to my systems. Please try again in a moment.", 
            HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
