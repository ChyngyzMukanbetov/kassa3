package com.example.kassa3.util.validation.enumValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MeasureNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MeasureNameValidation {
    String message() default "Invalid measure";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
