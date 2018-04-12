package ua.karatnyk.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.karatnyk.entity.TestTask;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.VideoYouTubeTask;

@Getter
@Setter
@NoArgsConstructor
public class LessonsForSudentsRequest {
	
	private int id;
	
	private String number;
	
	private String title;
	
	private String description;
	
	private List<TheoreticalTask> theoreticalTasks;
	
	private List<VideoYouTubeTask> videoYouTubeTasks;
	
	private List<TestTask> testTasks;
	
	

}
