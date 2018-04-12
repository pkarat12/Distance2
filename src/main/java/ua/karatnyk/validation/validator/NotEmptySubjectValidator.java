package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.validation.annotation.NotEmptySubject;

@Component
public class NotEmptySubjectValidator implements ConstraintValidator<NotEmptySubject, Subject> {

	@Override
	public void initialize(NotEmptySubject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Subject subject, ConstraintValidatorContext arg1) {
		return subject != null;
	}

}
