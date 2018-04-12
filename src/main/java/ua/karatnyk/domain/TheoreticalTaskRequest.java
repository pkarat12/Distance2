package ua.karatnyk.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.MaxSizeString;
import ua.karatnyk.validation.annotation.NotDocumentFile;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullString;

@Getter
@Setter
@NoArgsConstructor
public class TheoreticalTaskRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@MaxSizeString
	private String description;
	
	
	@NotDocumentFile
	private MultipartFile file;
	
	private int idTask;
	
	private String nameFile;
	
	private int userId;
	
}
