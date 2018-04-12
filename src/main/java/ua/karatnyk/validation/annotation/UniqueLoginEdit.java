package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import ua.karatnyk.validation.validator.UniqueLoginEditValidator;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginEditValidator.class)
public @interface UniqueLoginEdit {
	
	String message() default "not unique login";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
