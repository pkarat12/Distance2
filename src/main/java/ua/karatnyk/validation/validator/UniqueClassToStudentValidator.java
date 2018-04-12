package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.karatnyk.domain.ClassAddRequest;
import ua.karatnyk.service.ClassToStudentService;
import ua.karatnyk.validation.annotation.UniqueClassToStudent;

@Component
public class UniqueClassToStudentValidator implements ConstraintValidator<UniqueClassToStudent, ClassAddRequest>{
	
	@Autowired
	ClassToStudentService service;

	@Override
	public void initialize(UniqueClassToStudent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(ClassAddRequest classStudent, ConstraintValidatorContext arg1) {
		return service.findActiveClass(classStudent.getLetterToGrade(), classStudent.getGrade()) == null;
	}



}
