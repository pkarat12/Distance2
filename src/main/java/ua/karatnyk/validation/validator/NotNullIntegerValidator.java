package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.NotNullInteger;

@Component
public class NotNullIntegerValidator implements ConstraintValidator<NotNullInteger, Integer>{

	@Override
	public void initialize(NotNullInteger arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer arg0, ConstraintValidatorContext arg1) {
		return arg0 != null;
	}

}
