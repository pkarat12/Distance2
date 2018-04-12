package ua.karatnyk.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class ClassViewRequest {
	
	private String titleClass;
	
	private String classTeacher;
	
	private int id;
	
	private List<UserEntity> listStudentsInClass;

}
