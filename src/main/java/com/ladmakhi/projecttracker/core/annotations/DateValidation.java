package com.ladmakhi.projecttracker.core.annotations;

import com.ladmakhi.projecttracker.core.implementations.DataValidationImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataValidationImpl.class)
public @interface DateValidation {
    String message() default "You Must Your Date";

    boolean canBeNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
