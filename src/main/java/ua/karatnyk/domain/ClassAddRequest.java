package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;
import ua.karatnyk.validation.annotation.NotNullGrage;
import ua.karatnyk.validation.annotation.NotNullLetterToGrade;
import ua.karatnyk.validation.annotation.UniqueClassToStudent;

@Getter
@Setter
@NoArgsConstructor
@UniqueClassToStudent
public class ClassAddRequest {
	
	private UserEntity createdByUser;
	
	@NotNullGrage(message = "це поле не може бути порожнім")
	private Grade grade;
	
	@NotNullLetterToGrade(message = "це поле не може бути порожнім")
	private LetterToGrade letterToGrade;
	
	private UserEntity userClassTeacher;

}
