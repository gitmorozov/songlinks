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
      String password = user.getPassword();
      if (password == "" || password == null) return false;
      return password.equals(user.getMatchingPassword());
  }
}