package com.foolish.schoolmanagement.validations;

import com.foolish.schoolmanagement.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String > {
  List<String> weaksPasswords;

  @Override
  public void initialize(PasswordValidator constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    weaksPasswords = Arrays.asList("123456", "password", "qwerty");
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return (s != null && !weaksPasswords.contains(s));
  }
}
