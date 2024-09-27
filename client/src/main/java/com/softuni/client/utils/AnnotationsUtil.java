package com.softuni.client.utils;

import jakarta.validation.ConstraintValidatorContext;

public enum AnnotationsUtil {
    ;
    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
