package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class GroupFilter {
	
	private String title;
	
	private UserEntity user;

	public GroupFilter(String title, UserEntity user) {
		super();
		this.title = title;
		this.user = user;
	}
	
	

}
