package com.project.LearningNavigator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.LearningNavigator.exception.DatabaseOperationException;
import com.project.LearningNavigator.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<> (ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<?> handleDatabaseOperationException(DatabaseOperationException ex) {
        return new ResponseEntity<> ("DataBase error: "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> handlegenericException(Exception ex) {
        return new ResponseEntity<> ("An unexpected error occurred: "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
