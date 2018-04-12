package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.NotNullString;

@Component
public class NotNullStringValidator implements ConstraintValidator<NotNullString, String> {

	@Override
	public void initialize(NotNullString arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return arg0 != null;
	}

}
