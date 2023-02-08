package com.example.kassa3.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ColorNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColorNameValidation {
    String message() default "Invalid color";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
