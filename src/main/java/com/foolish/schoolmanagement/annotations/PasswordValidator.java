package com.foolish.schoolmanagement.annotations;

import com.foolish.schoolmanagement.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
  String message() default "* Please choose a strong password";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
