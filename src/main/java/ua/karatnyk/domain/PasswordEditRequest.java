package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.EqualsPassword;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
@EqualsPassword(message = "Паролі не співпадають")
public class PasswordEditRequest {
	
	private String entered;

	@NotNullString
	@NotEmpty
	private String newPassword;
	
	private String confirmPassword;
	
	private int id;
	
	public PasswordEditRequest(int id) {
		super();
		this.id = id;
	}
}
