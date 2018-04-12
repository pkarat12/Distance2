package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.validation.annotation.IsClassToStudent;

@Component
public class IsClassToStudentValidator implements ConstraintValidator<IsClassToStudent, Object>{

	@Override
	public void initialize(IsClassToStudent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext arg1) {
		try {
			return	ClassToStudent.class.isInstance(object);
		} catch (ClassCastException e) {
			return false;
		} 
		catch (NullPointerException e) {
			return false;
		}
	}


}
