package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import ua.karatnyk.domain.LessonFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.UserEntity;

public interface LessonService {
	
	List<Lesson> findAllNotDeletedByUserByCourse(UserEntity entity, Course course);
	Page<Lesson> findAllPagesNotDeletedByUserByCourse(int pageNumber, int pageSize, String sort, String sortByField, UserEntity entity, Course course);
	Page<Lesson> findAllPagesNotDeletedByUserByCourseWithFilter(int pageNumber, int pageSize, String sort, String sortByField, UserEntity entity, Course course, LessonFilter filter);
	List<Lesson> findAllNotDeletedByUserByCourseWithFilter(UserEntity entity, Course course, LessonFilter filter);
	
	void save(Lesson lesson);
	
	Lesson findByIdNotDeletedByUser(UserEntity user, int id);
	
	void changeStatus(Boolean visibility, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void delete(UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void update(String title, String description, String number, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	Page<Lesson> find(int pageNumber, int pageSize, String sort, String sortByField, Course course);
	
	List<Lesson> find(Course course, String search);
	
	Lesson find(Course course, int id);
	Lesson find(int id);
	
}
