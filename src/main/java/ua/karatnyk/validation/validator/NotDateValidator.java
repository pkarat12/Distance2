package ua.karatnyk.validation.validator;

import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.validation.annotation.NotDate;

@Component
public class NotDateValidator implements ConstraintValidator<NotDate, String>{

	@Override
	public void initialize(NotDate arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext arg1) {
		if(date == null || date.isEmpty()) {
			return true;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			format.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
