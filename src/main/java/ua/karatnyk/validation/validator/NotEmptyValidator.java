package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.NotEmpty;

@Component
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String>{

	@Override
	public void initialize(NotEmpty arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String text, ConstraintValidatorContext arg1) {
		return !text.isEmpty() && !text.trim().isEmpty();
		
	}

}
