package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ua.karatnyk.validation.annotation.MaxSizeFile;

@Component
public class MaxSizeFileValidator implements ConstraintValidator<MaxSizeFile, MultipartFile>{

	@Override
	public void initialize(MaxSizeFile arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext arg1) {
		try {
			if(file.getSize() <= 10*1024*1024)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
