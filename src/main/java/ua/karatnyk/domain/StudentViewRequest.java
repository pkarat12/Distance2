package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentViewRequest {
	
	private int id;
	private String fotoInBydeCode;
	private String firstName;
	private String lastName;
	private String middleName;
	private String classStudent;
	private String login;
	private String status;
	private String email;
	private String birthdate;
}
