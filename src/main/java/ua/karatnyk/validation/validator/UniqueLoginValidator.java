package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.karatnyk.service.UserService;
import ua.karatnyk.validation.annotation.UniqueLogin;

@Component
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String>{
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniqueLogin arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext arg1) {
		return userService.findByLogin(login) == null;
	}

}
