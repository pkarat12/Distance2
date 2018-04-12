package ua.karatnyk.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskFilter {
	
	private String search;

	public TaskFilter(String search) {
		super();
		this.search = search;
	}

}
