package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import ua.karatnyk.domain.VideoYouTubeTaskFilter;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;

public interface VideoYouTubeService {
	
	List<VideoYouTubeTask> findAllNotDeletedByUser(UserEntity entity);
	
	Page<VideoYouTubeTask> getPagesVideoYouTubeTasksByUser(int pageNumber, int pageSize, String sort, String sortByField, UserEntity userEntity);
	
	Page<VideoYouTubeTask> getPagesVideoYouTubeTasksWithFilter(int pageNumber, int pageSize, String sort, String sortByField, VideoYouTubeTaskFilter filter);
	List<VideoYouTubeTask> getVideoYouTubeTasksWithFilter(VideoYouTubeTaskFilter filter);
	
	void save(VideoYouTubeTask task);
	
	VideoYouTubeTask findTaskByIdNotDeleted(int id, UserEntity userEntity);
	
	void update(String title, String description, String link, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void update(UserEntity currentUser, Date lastUpdatedAt, int id); 
	
	VideoYouTubeTask find(Lesson lesson, int id);

}
