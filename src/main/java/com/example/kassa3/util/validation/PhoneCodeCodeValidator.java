package com.example.kassa3.util.validation;

import com.example.kassa3.model.enums.PhoneCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneCodeCodeValidator implements ConstraintValidator<PhoneCodeCodeValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return PhoneCode.getPhoneCodeByCode(value).isPresent();
    }
}
