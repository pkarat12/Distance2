package ua.karatnyk.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.NotEmail;

@Component
public class NotEmailValidator implements ConstraintValidator<NotEmail, String>{

	@Override
	public void initialize(NotEmail arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		if(email == null || email.isEmpty()) 
			return true;
		return Pattern.matches("[a-zA-Z0-9\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}$", email);
	}
	
	

}
