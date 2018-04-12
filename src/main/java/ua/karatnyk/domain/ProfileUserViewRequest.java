package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUserViewRequest {
	
	private int id;
	
	private String login;//
	
	private String firstName;//
	
	private String lastName;//
	
	private String middleName;//
	
	private String email;//

	private String numberSchool;//
	
	private String birthDate;//
	
	private String encodedToByteByNameFoto;//
	
	private String classTeacherFullName; //ім'я класного керівника. Тільки для Учня
	
	private String classInStudy; //клас, в якому навчається учень. Тільки для Учня
	
	private String classTeach; //клас, в якому вчитель є класним керівником . Тільки для Вчителя

	private String subject; //предмет, який викладає вчитель. Тільки для Вчителя
	
	

}
