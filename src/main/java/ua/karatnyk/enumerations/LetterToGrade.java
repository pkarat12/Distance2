package ua.karatnyk.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LetterToGrade {
	
	A("А"), B("Б"), C("В"), D("Г"), E("Д"), F("Е");
	
	public String title;

}
