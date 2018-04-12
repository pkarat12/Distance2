package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import ua.karatnyk.domain.TheoreticalTasksFilter;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;

public interface TheoreticalTaskService {
	
	List<TheoreticalTask> findAllNotDeletedByUser(UserEntity entity);
	
	Page<TheoreticalTask> getPagesTheoreticalTasksByUser(int pageNumber, int pageSize, String sort, String sortByField, UserEntity userEntity);
	
	Page<TheoreticalTask> getPagesTheoreticalTasksWithFilter(int pageNumber, int pageSize, String sort, String sortByField, TheoreticalTasksFilter filter);
	List<TheoreticalTask> getTheoreticalTasksWithFilter(TheoreticalTasksFilter filter);
	
	void save(TheoreticalTask task);
	
	TheoreticalTask findTaskByIdNotDeleted(int id, UserEntity userEntity);
	
	void update(String title, String description, String fileName, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void update(String title, String description, UserEntity currentUser, Date lastUpdatedAt, int id); 
	
	void update(UserEntity currentUser, Date lastUpdatedAt, int id); 
	
	TheoreticalTask find(Lesson lesson, int id);
	
}
