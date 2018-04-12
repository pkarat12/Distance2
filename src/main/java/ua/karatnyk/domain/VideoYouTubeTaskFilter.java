package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class VideoYouTubeTaskFilter {
	
private String title;
	
	private UserEntity userEntity;

	public VideoYouTubeTaskFilter(String title, UserEntity userEntity) {
		super();
		this.title = title;
		this.userEntity = userEntity;
	}

}
