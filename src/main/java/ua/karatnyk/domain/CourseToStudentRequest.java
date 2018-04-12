package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CourseToStudentRequest {
	
	private int idCourses;
	private String subject;
	private int amountTask;
}
