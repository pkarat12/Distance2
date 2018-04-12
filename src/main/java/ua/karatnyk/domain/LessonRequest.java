package ua.karatnyk.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.TestTask;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.validation.annotation.MaxSizeString;
import ua.karatnyk.validation.annotation.NotEmpty;
import ua.karatnyk.validation.annotation.NotNullString;
import ua.karatnyk.validation.annotation.NotPossitiveIntegerNumber;

@Getter
@Setter
@NoArgsConstructor
public class LessonRequest {
	
	@NotNullString
	@NotEmpty
	private String title;
	
	@MaxSizeString
	private String description;
	
	private Course course;
	
	private boolean visibility;
	
	private int idLesson;
	private UserEntity user;
	
	@NotPossitiveIntegerNumber
	private String number;
	
	private int idUser;
	
	private List<TheoreticalTask> theoreticalTasks;
	
	private List<TheoreticalTask> newTheoreticalTasks = new ArrayList<>();
	
	private List<VideoYouTubeTask> videoYouTubeTasks;
	private List<VideoYouTubeTask> newVideoYouTubeTasks = new ArrayList<>();
	
	private List<TestTask> testTasks;
	private List<TestTask> newTestTasks = new ArrayList<>();

}
