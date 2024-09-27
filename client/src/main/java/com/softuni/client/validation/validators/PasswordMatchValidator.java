package com.softuni.client.validation.validators;

import com.softuni.client.domain.dto.user.RegisterDto;
import com.softuni.client.utils.AnnotationsUtil;
import com.softuni.client.validation.annotations.PasswordMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterDto> {

    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RegisterDto userRegisterBindingModel, ConstraintValidatorContext context) {

        final String password = userRegisterBindingModel.getPassword();
        final String confirmPassword = userRegisterBindingModel.getConfirmPassword();

        boolean passwordMatch = password != null && password.equals(confirmPassword);

        if (!passwordMatch) {
            AnnotationsUtil.setErrorMessage(context,message);
            return false;
        }

        return true;
    }

}
