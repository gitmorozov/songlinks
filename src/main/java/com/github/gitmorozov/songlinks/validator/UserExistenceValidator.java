package com.github.gitmorozov.songlinks.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.gitmorozov.songlinks.entity.User;
import com.github.gitmorozov.songlinks.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserExistenceValidator implements ConstraintValidator<UserNotExist, Object> {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean isValid(Object username, ConstraintValidatorContext context) {
		User user = userRepo.findByUsername((String) username);
		return user == null;

	}

}
