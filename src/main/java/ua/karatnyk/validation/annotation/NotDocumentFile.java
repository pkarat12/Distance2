package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.NotDocumentFileValidator;


@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDocumentFileValidator.class)
public @interface NotDocumentFile {
	
	String message() default "не вірний формат файлу, оберіть pdf документ";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
