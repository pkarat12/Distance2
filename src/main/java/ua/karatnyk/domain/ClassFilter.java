package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Grade;

@Getter
@Setter
@NoArgsConstructor
public class ClassFilter {
	
	private Grade grade;

	public ClassFilter(Grade grade) {
		super();
		this.grade = grade;
	}
	
	

}
