package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ua.karatnyk.domain.LessonRequest;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TestTask;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;

public class LessonMapper {
	
	public static Lesson requestToLessonForAdd(LessonRequest request, UserEntity currentUser) {
		Lesson lesson = new Lesson();
		
		lesson.setCourse(request.getCourse());
		lesson.setCreatedByUser(currentUser);
		lesson.setDescription(request.getDescription());
		lesson.setLastUpdateByUser(currentUser);
		lesson.setNumber(request.getNumber());		
		lesson.setTitle(request.getTitle());
		return lesson;
	}
	
	public static LessonRequest lessonToRequestForView(Lesson lesson) {
		
		LessonRequest request = new LessonRequest();
		
		request.setCourse(lesson.getCourse());
		request.setDescription(lesson.getDescription());
		request.setIdUser(lesson.getCreatedByUser().getId());
		request.setNumber(lesson.getNumber());
		request.setTestTasks(lesson.getTaskTests());
		request.setTheoreticalTasks(lesson.getTaskTheoreticals());
		request.setTitle(lesson.getTitle());
		request.setVideoYouTubeTasks(lesson.getTaskVideoYouTubes());
		request.setVisibility(lesson.isVisibility());
		request.setIdLesson(lesson.getId());
		request.setUser(lesson.getCreatedByUser());
		return request;
	}
	
	public static List<LessonRequest> lessonsToRequestsForView(List<Lesson> lessons) {
		List<LessonRequest> requests = new ArrayList<>();
		for(Lesson l: lessons) {
			LessonRequest request = LessonMapper.lessonToRequestForView(l);
			requests.add(request);
		}
		return requests;
	}
	public static Lesson requestToLessonForAddTasks(LessonRequest request, UserEntity currentUser) {
		Lesson lesson = new Lesson();
		lesson.setCourse(request.getCourse());
		lesson.setCreatedByUser(request.getUser());
		lesson.setDescription(request.getDescription());
		lesson.setId(request.getIdLesson());
		lesson.setLastUpdatedAt(new Date());
		lesson.setNumber(request.getNumber());
		
		List<TheoreticalTask> theoreticalTasks = request.getTheoreticalTasks();
		if(request.getNewTheoreticalTasks() != null)
			theoreticalTasks.addAll(request.getNewTheoreticalTasks());
		lesson.setTaskTheoreticals(theoreticalTasks);
		
		List<TestTask> testTasks = request.getTestTasks();
		if(request.getNewTestTasks() != null)
			testTasks.addAll(request.getNewTestTasks());
		lesson.setTaskTests(testTasks);
	
		List<VideoYouTubeTask> videoYouTubeTasks = request.getVideoYouTubeTasks();
		if(request.getNewVideoYouTubeTasks() != null)
			videoYouTubeTasks.addAll(request.getNewVideoYouTubeTasks());
		lesson.setTaskVideoYouTubes(videoYouTubeTasks);

		lesson.setLastUpdateByUser(currentUser);
		lesson.setTitle(request.getTitle());
		lesson.setVisibility(request.isVisibility());
		return lesson;
	}
	
	public static Lesson requestToLessonForRemoveTasks(LessonRequest request, UserEntity currentUser) {
		Lesson lesson = new Lesson();
		lesson.setCourse(request.getCourse());
		lesson.setCreatedByUser(request.getUser());
		lesson.setDescription(request.getDescription());
		lesson.setId(request.getIdLesson());
		lesson.setLastUpdatedAt(new Date());
		lesson.setNumber(request.getNumber());
		List<TheoreticalTask> theoreticalTasks = request.getTheoreticalTasks();
		if(request.getNewTheoreticalTasks() != null)
			theoreticalTasks.removeAll(request.getNewTheoreticalTasks());
		lesson.setTaskTheoreticals(theoreticalTasks);
		
		List<TestTask> testTasks = request.getTestTasks();
		if(request.getNewTestTasks() != null)
			testTasks.removeAll(request.getNewTestTasks());
		lesson.setTaskTests(testTasks);
		List<VideoYouTubeTask> videoYouTubeTasks = request.getVideoYouTubeTasks();
		if(request.getNewVideoYouTubeTasks() != null)
			videoYouTubeTasks.removeAll(request.getNewVideoYouTubeTasks());
		lesson.setTaskVideoYouTubes(videoYouTubeTasks);
		lesson.setLastUpdateByUser(currentUser);
		lesson.setTitle(request.getTitle());
		lesson.setVisibility(request.isVisibility());
		return lesson;
	}

}
