package ua.karatnyk.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.MaxSizeFile;
import ua.karatnyk.validation.annotation.NotDate;
import ua.karatnyk.validation.annotation.NotEmail;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotImage;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUserEditRequest {
	
	private int id;
	
	private String nameFoto;
	
	@NotImage
	@MaxSizeFile
	private MultipartFile file;
	
	@NotNullString
	@NotEmpty
	private String firstName;
	
	@NotNullString
	@NotEmpty
	private String lastName;
	
	private String middleName;
	
	@NotEmail
	private String email;
	@NotDate
	private String birthDate;

}
