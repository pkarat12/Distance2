package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class ClassEditRequest {
	
	
	private UserEntity userClassTeacher;
	
	private int id;


}
