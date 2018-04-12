package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.EqualsPasswordValidator;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualsPasswordValidator.class)
public @interface EqualsPassword {
	String message() default "not equals with current paswword";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	

}
