package ua.karatnyk.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.NotImageValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotImageValidator.class)
public @interface NotImage {
	
	String message() default "файл не є зображенням";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
