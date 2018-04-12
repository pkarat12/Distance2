package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import ua.karatnyk.domain.CourseFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;

public interface CourseService {
	
	List<Course> findAllNotDeletedByUser(UserEntity entity);
	
	Page<Course> getPagesCoursesByUser(int pageNumber, int pageSize, String sort, String sortByField, UserEntity userEntity);
	
	Page<Course> getPagesCoursesWithFilter(int pageNumber, int pageSize, String sort, String sortByField, CourseFilter filter);
	List<Course> getCoursesWithFilter(CourseFilter filter);
	
	void save(Course course);
	
	Course findCourseByIdNotDeletedByUser(int id, UserEntity user);
	Course findCourseByIdNotDeleted(int id);
	
	void update(String title, String description, Grade grade, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void delete(UserEntity currentUser, Date lastUpdatedAt, int id); 
	
}
