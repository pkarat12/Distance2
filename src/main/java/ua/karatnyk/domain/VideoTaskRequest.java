package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.MaxSizeString;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class VideoTaskRequest {
	
	private int idTask;
	
	@NotNullString
	@NotEmpty
	private String link;
	
	@MaxSizeString
	private String description;
	
	@NotNullString
	@NotEmpty
	private String title;

}
