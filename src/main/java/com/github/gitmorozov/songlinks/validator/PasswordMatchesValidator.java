package com.github.gitmorozov.songlinks.validator;

import com.github.gitmorozov.songlinks.dto.UserDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
implements ConstraintValidator<PasswordMatches, Object> {
  
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){
      UserDto user = (UserDto) obj;
      return user.getPassword().equals(user.getMatchingPassword());
  }
}