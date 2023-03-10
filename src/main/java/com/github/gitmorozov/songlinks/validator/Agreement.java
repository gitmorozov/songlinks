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
@Constraint(validatedBy = AgreementValidator.class)
@Documented
public @interface Agreement {
    String message() default "Accept agreement terms";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}