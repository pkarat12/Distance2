package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.domain.PasswordEditRequest;
import ua.karatnyk.validation.annotation.EqualsPassword;


@Component
public class EqualsPasswordValidator implements ConstraintValidator<EqualsPassword, PasswordEditRequest>{
	
	@Override
	public void initialize(EqualsPassword arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(PasswordEditRequest request, ConstraintValidatorContext arg1) {
		return request.getNewPassword().equals(request.getConfirmPassword());
	}

	

}
