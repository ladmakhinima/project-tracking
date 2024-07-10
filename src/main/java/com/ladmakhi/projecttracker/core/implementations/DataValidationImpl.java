package com.ladmakhi.projecttracker.core.implementations;

import com.ladmakhi.projecttracker.core.annotations.DateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataValidationImpl implements ConstraintValidator<DateValidation, String> {
    private boolean canBeNull;

    @Override
    public void initialize(DateValidation constraintAnnotation) {
        this.canBeNull = constraintAnnotation.canBeNull();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (value == null && canBeNull) return true;
            if (value == null) return false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate.parse(value, formatter);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
