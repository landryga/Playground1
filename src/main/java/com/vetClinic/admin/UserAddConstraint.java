package com.vetClinic.admin;

import  java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = UserValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAddConstraint {
	String message() default "Name must be unique </br>";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
