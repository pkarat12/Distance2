package ua.karatnyk.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullCourse;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class GroupRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@NotNullCourse
	private Course course;
	
	private UserEntity user;
	
	private int idGroup;
	
	private List<UserEntity> students;
	
	private List<UserEntity> newStudents = new ArrayList<>();
	

}
