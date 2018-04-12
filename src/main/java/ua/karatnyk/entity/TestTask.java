package ua.karatnyk.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test", indexes = @Index(columnList = "title"))
@Getter
@Setter
@NoArgsConstructor
public class TestTask extends TaskBase{
	
	
	private String question;
	
	private String answer;
	
	@Column(name = "wrong_answers")
	private String[] wrongAnswers;
	
	
	@ManyToMany
	@JoinTable(name = "lesson_task_test", joinColumns = @JoinColumn(name = "task_test_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
	private List<Lesson> lessons = new ArrayList<>();

}
