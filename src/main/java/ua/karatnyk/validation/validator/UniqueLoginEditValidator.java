package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.karatnyk.domain.UserEditRequest;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.service.UserService;
import ua.karatnyk.validation.annotation.UniqueLoginEdit;

@Component
public class UniqueLoginEditValidator implements ConstraintValidator<UniqueLoginEdit, UserEditRequest>{
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniqueLoginEdit arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(UserEditRequest request, ConstraintValidatorContext arg1) {
		UserEntity entity = userService.findByLogin(request.getNewLogin());
		if(entity == null || entity.getLogin().equals(request.getOldLogin())) {
			return true;
		}
		return false;
	}

	

}
