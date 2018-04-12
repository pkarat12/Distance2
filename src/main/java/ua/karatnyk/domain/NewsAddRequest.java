package ua.karatnyk.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.validation.annotation.MaxSizeFile;
import ua.karatnyk.validation.annotation.MaxSizeString;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotImage;
import ua.karatnyk.validation.annotation.NotNullString;


@Getter
@Setter
@NoArgsConstructor
public class NewsAddRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@NotNullString
	@NotEmpty
	@MaxSizeString
	private String description;
	
	@NotImage
	@MaxSizeFile
	private MultipartFile file;

}
