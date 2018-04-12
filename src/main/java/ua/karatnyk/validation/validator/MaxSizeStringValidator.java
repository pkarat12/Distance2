package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.MaxSizeString;

@Component
public class MaxSizeStringValidator implements ConstraintValidator<MaxSizeString, String>{

	@Override
	public void initialize(MaxSizeString arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String text, ConstraintValidatorContext arg1) {
		if(text.length() <= 1000)
			return true;
		return false;
	}

}
