package com.icesi.backend.validation;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<CustomAnnotations.ValidEmail, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        String emailValidationRegex = "[A-Za-z\\d]+@[A-Za-z\\d]+\\.[A-Za-z]+(.[A-Za-z]+)?";

        return s.matches(emailValidationRegex);
    }
}
