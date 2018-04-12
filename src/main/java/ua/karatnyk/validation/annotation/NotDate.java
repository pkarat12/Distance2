package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.NotDateValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDateValidator.class)
public @interface NotDate {
	
	String message() default "неправильний формат дати, введіть дату у такому форматі(дд-мм-рррр)";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};


}
