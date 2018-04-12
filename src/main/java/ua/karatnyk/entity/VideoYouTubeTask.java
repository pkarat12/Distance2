package ua.karatnyk.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "video_youtube", indexes = @Index(columnList = "title"))
@Getter
@Setter
@NoArgsConstructor
public class VideoYouTubeTask extends TaskBase{
	
	private String link;
	
	@ManyToMany
	@JoinTable(name = "lesson_task_video_youtube", joinColumns = @JoinColumn(name = "task_video_youtube_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
	private List<Lesson> lessons = new ArrayList<>();

}
