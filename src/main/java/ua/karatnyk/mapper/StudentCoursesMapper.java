package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.CourseToStudentRequest;
import ua.karatnyk.domain.LessonsForSudentsRequest;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.Lesson;

public class StudentCoursesMapper {
	
	public static List<CourseToStudentRequest> coursesForLeftSide(List<Group> groups) {
		if(groups == null)
			return null;
		List<CourseToStudentRequest> list = new ArrayList<>();
		for(Group g: groups) {
			CourseToStudentRequest request = new CourseToStudentRequest();
			request.setIdCourses(g.getCourse().getId());
			request.setSubject(g.getCreatedByUser().getSubject().title.toUpperCase());
			int count = 0;
			for(Lesson l: g.getCourse().getLessons()) {
				count = count+l.getTaskTests().size()+l.getTaskTheoreticals().size()+l.getTaskVideoYouTubes().size();
			}
			request.setAmountTask(count);
			list.add(request);
		}
		return list;
	}
	
	public static List<LessonsForSudentsRequest> lessonsForStudentsRequest(List<Lesson> list) {
		if(list == null)
			return null;
		List<LessonsForSudentsRequest> requests = new ArrayList<>();
		for(Lesson l: list) {
			LessonsForSudentsRequest request = new LessonsForSudentsRequest();
			request.setDescription(l.getDescription());
			request.setId(l.getId());
			request.setNumber(l.getNumber());
			request.setTitle(l.getTitle());
			if(!l.getTaskTheoreticals().isEmpty())
				request.setTheoreticalTasks(l.getTaskTheoreticals());
			if(!l.getTaskVideoYouTubes().isEmpty())
				request.setVideoYouTubeTasks(l.getTaskVideoYouTubes());
			if(!l.getTaskTests().isEmpty())
				request.setTestTasks(l.getTaskTests());
			requests.add(request);
		}
		return requests;
	}

}
