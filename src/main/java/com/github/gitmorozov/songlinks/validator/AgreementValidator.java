package com.github.gitmorozov.songlinks.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgreementValidator implements ConstraintValidator<Agreement, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		return (boolean) obj;
	}

}
