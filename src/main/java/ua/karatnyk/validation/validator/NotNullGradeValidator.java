package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.validation.annotation.NotNullGrage;

@Component
public class NotNullGradeValidator implements ConstraintValidator<NotNullGrage, Grade>{

	@Override
	public void initialize(NotNullGrage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Grade arg0, ConstraintValidatorContext arg1) {
		return arg0 != null;
	}

}
