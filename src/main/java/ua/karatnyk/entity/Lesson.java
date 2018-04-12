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
@Table(name = "lessons", indexes = @Index(columnList = "title_lesson"))
@Getter
@Setter
@NoArgsConstructor
public class Lesson extends BaseEntity{

	
	@Column(name = "title_lesson")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	private boolean visibility;
	
	private String number;
	
	@ManyToMany
	@JoinTable(name = "lesson_task_theoretical", joinColumns = @JoinColumn(name = "lesson_id"), inverseJoinColumns = @JoinColumn(name = "task_teoretical_id"))
	private List<TheoreticalTask> taskTheoreticals = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "lesson_task_video_youtube", joinColumns = @JoinColumn(name = "lesson_id"), inverseJoinColumns = @JoinColumn(name = "task_video_youtube_id"))
	private List<VideoYouTubeTask> taskVideoYouTubes = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name = "lesson_task_test", joinColumns = @JoinColumn(name = "lesson_id"), inverseJoinColumns = @JoinColumn(name = "task_test_id"))
	private List<TestTask> taskTests = new ArrayList<>();
	
}
