package com.github.gitmorozov.songlinks.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.gitmorozov.songlinks.entity.Category;
import com.github.gitmorozov.songlinks.repository.CategoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ParentValidator implements ConstraintValidator<ParentExist, Object> {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public boolean isValid(Object categoryId, ConstraintValidatorContext context) {
		if ((long) categoryId == 0) {
			return true;
		}
		Category category = categoryRepo.findById((long) categoryId).orElse(null);
		return category != null;
	}

}