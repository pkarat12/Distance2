package ua.karatnyk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "news", indexes = @Index(columnList = "title"))
@Getter
@Setter
@NoArgsConstructor
public class News extends BaseEntity{
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "path_to_foto")
	private String nameFoto;
	
}
