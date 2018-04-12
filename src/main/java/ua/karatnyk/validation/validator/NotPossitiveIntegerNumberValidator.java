package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import ua.karatnyk.validation.annotation.NotPossitiveIntegerNumber;

@Component
public class NotPossitiveIntegerNumberValidator implements ConstraintValidator<NotPossitiveIntegerNumber, String> {

	@Override
	public void initialize(NotPossitiveIntegerNumber arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String text, ConstraintValidatorContext arg1) {
		try {
			int k = Integer.parseInt(text);
			if(k > 0)
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
