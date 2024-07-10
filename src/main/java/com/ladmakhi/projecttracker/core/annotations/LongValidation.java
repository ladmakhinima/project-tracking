package com.ladmakhi.projecttracker.core.annotations;


import com.ladmakhi.projecttracker.core.implementations.LongValidationImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongValidationImpl.class)
public @interface LongValidation {
    String message() default "Your Data Is Invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
