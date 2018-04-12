package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.Subject;

@Getter
@Setter
@NoArgsConstructor
public class TeacherFilter {
	
	private Role role = Role.ROLE_TEACHER;
	private String search;
	private Subject subject;
	
	public TeacherFilter(String search, Subject subject) {
		super();
		this.search = search.trim().toLowerCase();
		this.subject = subject;
	}

	
	
	
}
