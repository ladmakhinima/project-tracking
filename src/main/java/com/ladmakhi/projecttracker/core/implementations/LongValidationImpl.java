package com.ladmakhi.projecttracker.core.implementations;

import com.ladmakhi.projecttracker.core.annotations.LongValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongValidationImpl implements ConstraintValidator<LongValidation, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        try {
            if (value == null) return true;
            Long.parseLong(value.toString());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
