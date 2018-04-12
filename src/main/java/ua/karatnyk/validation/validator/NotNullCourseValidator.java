package ua.karatnyk.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.karatnyk.entity.Course;
import ua.karatnyk.validation.annotation.NotNullCourse;

@Component
public class NotNullCourseValidator implements ConstraintValidator<NotNullCourse, Course>{

	@Override
	public void initialize(NotNullCourse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Course course, ConstraintValidatorContext arg1) {
		return course != null;
	}

}
