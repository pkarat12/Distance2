package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import ua.karatnyk.enumerations.LetterToGrade;
import ua.karatnyk.validation.annotation.NotNullLetterToGrade;

@Component
public class NotNullLetterToGradeValidator implements ConstraintValidator<NotNullLetterToGrade, LetterToGrade>{

	@Override
	public void initialize(NotNullLetterToGrade arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(LetterToGrade arg0, ConstraintValidatorContext arg1) {
		return arg0 != null;
	}

}
