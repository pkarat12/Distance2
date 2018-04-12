package ua.karatnyk.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class TaskBase  extends BaseEntity{
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
}
