package ua.karatnyk.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Subject {
	
	INFORMATIC("Інформатика"), MATHEMATIC("Математика"), ENGLISH("Англійська мова"), PHYSICS("Фізика"), CHEMISTRY("Хімія"), UKRAINIAN("Українська мова та література"), HISTORY("Історія");
	
	public String title;

}
