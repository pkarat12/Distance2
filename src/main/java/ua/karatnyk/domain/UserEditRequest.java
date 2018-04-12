package ua.karatnyk.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.validation.annotation.MaxSizeFile;
import ua.karatnyk.validation.annotation.NotDate;
import ua.karatnyk.validation.annotation.NotEmail;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotImage;
import ua.karatnyk.validation.annotation.NotNullString;
import ua.karatnyk.validation.annotation.UniqueLoginEdit;

@Getter
@Setter
@NoArgsConstructor
@UniqueLoginEdit(message = "користувач з таким логіном вже існує")
public class UserEditRequest {
	
	public String oldLogin;
	
	@NotNullString
	@NotEmpty
	public String newLogin;
	
	@NotNullString
	@NotEmpty
	private String firstName;
	
	@NotNullString
	@NotEmpty
	private String lastName;
	
	private int id;
	
	private String middleName;
	
	private Subject subject;
	
	private String nameFoto;
	
	@NotImage
	@MaxSizeFile
	private MultipartFile file;
	
	@NotDate
	private String birthDate;
	
	@NotEmail
	private String email;
	
	private ClassToStudent classStudent;
	
	private Role role;

}
