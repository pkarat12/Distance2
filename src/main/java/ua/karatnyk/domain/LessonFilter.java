package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonFilter {

	private String title;

	public LessonFilter(String title) {
		super();
		this.title = title;
	}
}
