package com.kalinowski.test5.exception;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationExceptionDto extends ExceptionDto {
    private final List<FieldViolationInfo> violations = new ArrayList<>();

    public ValidationExceptionDto() {
        super(LocalDateTime.now(), "Validation exception");
    }

    public ValidationExceptionDto addViolation(String field, String message) {
        violations.add(new FieldViolationInfo(field, message));
        return this;
    }

    @Value
    public static class FieldViolationInfo {
        String field;
        String message;
    }
}
