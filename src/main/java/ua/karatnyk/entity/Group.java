package ua.karatnyk.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "groups", indexes = @Index(columnList = "group_title"))
@Getter
@Setter
@NoArgsConstructor
public class Group extends BaseEntity{

	@Column(name = "group_title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> users = new ArrayList<UserEntity>();

}
