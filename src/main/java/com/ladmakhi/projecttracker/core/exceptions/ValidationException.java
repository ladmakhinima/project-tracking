package com.ladmakhi.projecttracker.core.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<String, String>();
        exception.getAllErrors().stream().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String error = e.getDefaultMessage();
            errors.put(fieldName, error);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
