package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.NotEmailValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmailValidator.class)
public @interface NotEmail {
	
	String message() default "введене значення не відповідає формату пошти";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
