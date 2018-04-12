package ua.karatnyk.service.utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
	
	private String from;
	private String to;
	private String subject;
	private String content;
	
	
	public Mail() {
		this.from = "karatnyk.test@gmail.com";
	}

}
