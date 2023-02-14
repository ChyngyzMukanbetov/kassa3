package com.example.kassa3.util.validation.enumValidation;

import com.example.kassa3.model.enums.Color;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ColorNameValidator implements ConstraintValidator<ColorNameValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Color.getColorByName(value).isPresent();
    }
}
