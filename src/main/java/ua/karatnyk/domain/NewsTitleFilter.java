package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsTitleFilter {
	private String search;

	public NewsTitleFilter(String search) {
		super();
		this.search = search;
	}

}
