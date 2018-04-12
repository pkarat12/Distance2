package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.MaxSizeFileValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxSizeFileValidator.class)
public @interface MaxSizeFile {
	

	String message() default "розмір зображення не може перевищувати 10MB";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
