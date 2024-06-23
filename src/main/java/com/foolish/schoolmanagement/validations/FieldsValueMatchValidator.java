package com.foolish.schoolmanagement.validations;

import com.foolish.schoolmanagement.annotations.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
  private String field;
  private String fieldMatch;
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public void initialize(FieldsValueMatch constraintAnnotation) {
    this.field = constraintAnnotation.field();
    this.fieldMatch = constraintAnnotation.fieldMatch();
    this.passwordEncoder = new BCryptPasswordEncoder(16);
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    Object fieldValue = new BeanWrapperImpl(value)
            .getPropertyValue(field);
    Object fieldMatchValue = new BeanWrapperImpl(value)
            .getPropertyValue(fieldMatch);
    if (fieldValue != null) {
      return fieldValue.equals(fieldMatchValue) || passwordEncoder.matches((String)fieldMatchValue, (String)fieldValue);
    } else {
      return fieldMatchValue == null;
    }
  }
}