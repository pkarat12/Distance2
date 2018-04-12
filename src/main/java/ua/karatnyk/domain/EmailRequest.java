package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.NotEmail;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class EmailRequest {
	
	@NotEmpty
	@NotNullString
	@NotEmail
	private String email;
	private int id;
}
