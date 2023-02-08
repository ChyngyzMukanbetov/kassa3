package com.example.kassa3.util.validation;

import com.example.kassa3.model.enums.Measure;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MeasureNameValidator implements ConstraintValidator<MeasureNameValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Measure.getMeasureByName(value).isPresent();
    }
}
