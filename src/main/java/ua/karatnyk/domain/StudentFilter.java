package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.enumerations.Role;

@Getter
@Setter
@NoArgsConstructor
public class StudentFilter {
	
	
	private Role role = Role.ROLE_STUDENT;
	private String search;
	private ClassToStudent classToStudent;
	
	public StudentFilter(String search, ClassToStudent classToStudent) {
		super();
		this.search = search.trim().toLowerCase();
		this.classToStudent = classToStudent;
	}
}
