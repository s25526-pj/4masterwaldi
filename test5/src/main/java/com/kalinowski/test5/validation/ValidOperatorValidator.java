package com.kalinowski.test5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ValidOperatorValidator implements ConstraintValidator<ValidOperator, Character> {

    private static final List<Character> allowedOperators = List.of('+', '-', '*', '/');

    @Override
    public boolean isValid(Character operator, ConstraintValidatorContext constraintValidatorContext) {
        return allowedOperators.contains(operator);
    }
}
