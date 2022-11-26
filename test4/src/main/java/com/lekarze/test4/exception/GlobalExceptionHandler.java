package com.lekarze.test4.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleEntityNotFoundException(EntityNotFoundException exception) {
        return ExceptionDto.fromException(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDto handleEntityNotFoundException(MethodArgumentNotValidException exception) {
        ValidationExceptionDto dto = new ValidationExceptionDto();
        exception.getFieldErrors().forEach(fieldError ->
                dto.addViolation(fieldError.getField(), fieldError.getDefaultMessage()));
        return dto;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleFieldNotUniqueException(DataIntegrityViolationException exception) {
        return ExceptionDto.fromException(exception);
    }
}
