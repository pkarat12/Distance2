package ua.karatnyk.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.karatnyk.validation.validator.NotNullLetterToGradeValidator;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullLetterToGradeValidator.class)
public @interface NotNullLetterToGrade {
	
	String message() default "це поле не може бути порожнім";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
