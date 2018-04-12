package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.validation.annotation.MaxSizeString;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullGrage;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@MaxSizeString
	private String description;
	
	private int idCourse;
	
	@NotNullGrage
	private Grade grade;
	
	private int idUser;

}
