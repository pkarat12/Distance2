package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.MaxSizeStringValidator;
	
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxSizeStringValidator.class)

public @interface MaxSizeString {
		
	String message() default "розмір тексту не може перевищувати 1000 символів";
		
	Class<?>[] groups() default {};
		
	Class<? extends Payload>[] payload() default {};

}

