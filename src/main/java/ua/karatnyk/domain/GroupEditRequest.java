package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullCourse;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class GroupEditRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@NotNullCourse
	private Course course;
	
	private int idGroup;

}
