package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotEmptySubject;
import ua.karatnyk.validation.annotation.NotNullString;
import ua.karatnyk.validation.annotation.UniqueLogin;

@Getter
@Setter
@NoArgsConstructor
public class TeacherAddRequest {
	
	@NotNullString
	@NotEmpty
	private String firstName;
	
	@NotNullString
	@NotEmpty
	private String lastName;
	
	@NotNullString
	@NotEmpty
	private String middleName;
	
	@NotNullString
	@NotEmpty
	@UniqueLogin
	private String login;
	
	@NotNullString
	@NotEmpty
	private String password;
	
	@NotEmptySubject
	private Subject subject;

}
