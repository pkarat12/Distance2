package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsViewRequest {
	
	
	private int id;
	private String title;
	private String description;
	private String encodedToByte;
	private int idUser;
	private String fullNameUser;

}
