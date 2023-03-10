package com.github.gitmorozov.songlinks.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.gitmorozov.songlinks.entity.User;
import com.github.gitmorozov.songlinks.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailExistenceValidator implements ConstraintValidator<EmailNotExist, Object> {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean isValid(Object email, ConstraintValidatorContext context) {
		User user = userRepo.findByEmail((String) email);
		return user == null;
	}

}
