package com.kalinowski.test5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ValidationExceptionDto dto = new ValidationExceptionDto();
        exception.getFieldErrors().forEach(fieldError ->
                dto.addViolation(fieldError.getField(), fieldError.getDefaultMessage()));
        return dto;
    }

}
