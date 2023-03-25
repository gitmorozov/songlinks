package com.github.gitmorozov.songlinks.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.gitmorozov.songlinks.entity.Category;
import com.github.gitmorozov.songlinks.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryNameValidator implements ConstraintValidator<CategoryNotExist, Object> {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public boolean isValid(Object categoryName, ConstraintValidatorContext context) {
		Category category = categoryRepo.findByName((String) categoryName);
		return category == null;
	}

}
