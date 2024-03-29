package com.github.gitmorozov.songlinks.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailExistenceValidator.class)
@Documented
public @interface EmailNotExist {
    String message() default "User with this email exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}