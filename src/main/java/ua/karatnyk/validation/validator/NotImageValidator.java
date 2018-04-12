package ua.karatnyk.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ua.karatnyk.validation.annotation.NotImage;

@Component
public class NotImageValidator implements ConstraintValidator<NotImage, MultipartFile>{

	@Override
	public void initialize(NotImage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext arg1) {
		String nameFile = file.getOriginalFilename();
		if(file.isEmpty())
			return true;
		return Pattern.matches("[^\\s]+(\\.(?i)(png|jpg|jpeg|bmp))$", nameFile);
	}

}
