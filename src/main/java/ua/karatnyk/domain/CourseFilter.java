package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;

@Getter
@Setter
@NoArgsConstructor
public class CourseFilter {
	
	private Grade grade;
	
	private UserEntity userEntity;
	
	private String title;

	public CourseFilter(Grade grade, UserEntity userEntity, String title) {
		super();
		this.grade = grade;
		this.userEntity = userEntity;
		this.title = title;
	}
	
	

}
