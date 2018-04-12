package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import ua.karatnyk.validation.validator.UniqueLoginValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
	
	String message() default "користувач з таким логіном вже існує";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
