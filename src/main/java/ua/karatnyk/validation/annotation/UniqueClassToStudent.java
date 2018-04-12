package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.UniqueClassToStudentValidator;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueClassToStudentValidator.class)
public @interface UniqueClassToStudent {
	
	String message() default "такий клас вже існує";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
