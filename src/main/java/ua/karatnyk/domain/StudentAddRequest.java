package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.IsClassToStudent;
import ua.karatnyk.validation.annotation.NotNullString;
import ua.karatnyk.validation.annotation.UniqueLogin;

@Getter
@Setter
@NoArgsConstructor
public class StudentAddRequest {
	
	@NotNullString
	@NotEmpty
	private String firstName;
	
	@NotNullString
	@NotEmpty
	private String lastName;
	
	private String middleName;
	
	@NotNullString
	@NotEmpty
	@UniqueLogin
	private String login;
	
	@NotNullString
	@NotEmpty
	private String password;
	
	@IsClassToStudent(message = "це поле не може бути порожнім")
	private ClassToStudent classStudent;

}
