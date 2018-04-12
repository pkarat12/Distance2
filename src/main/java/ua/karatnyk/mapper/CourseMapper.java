package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.CourseRequest;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.UserEntity;

public class CourseMapper {
	
	public static Course requestToEntityForAdd(CourseRequest request, UserEntity currentUser) {
		Course course = new Course();
		course.setCreatedByUser(currentUser);
		course.setLastUpdateByUser(currentUser);
		course.setDescription(request.getDescription());
		course.setGrade(request.getGrade());
		course.setTitle(request.getTitle());
		return course;
	}
	
	public static CourseRequest entityToRequestForView(Course course) {
		CourseRequest request = new CourseRequest();
		request.setDescription(course.getDescription());
		request.setGrade(course.getGrade());
		request.setIdCourse(course.getId());
		request.setIdUser(course.getCreatedByUser().getId());
		request.setTitle(course.getTitle());
		return request;
	}
	
	public static List<CourseRequest> entitiesToRequestsForView(List<Course> courses) {
		List<CourseRequest> requests = new ArrayList<>();
		for(Course c: courses) {
			CourseRequest request = CourseMapper.entityToRequestForView(c);
			requests.add(request);
		}
		return requests;
	}

}
