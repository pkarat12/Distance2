package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.VideoTaskRequest;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.service.utilities.Utilities;
import ua.karatnyk.entity.UserEntity;

public class VideoYouTubeTaskMapper {
	
	public static VideoYouTubeTask taskRequestToTaskForSave(VideoTaskRequest request, UserEntity currentUser) {
		VideoYouTubeTask task = new VideoYouTubeTask();	
		task.setCreatedByUser(currentUser);
		task.setLastUpdateByUser(currentUser);
		task.setDescription(request.getDescription());
		task.setTitle(request.getTitle());
		task.setLink(request.getLink());
		return task;
		
	}
	
	public static VideoTaskRequest taskToRequestViewProfile(VideoYouTubeTask task) {
		VideoTaskRequest request = new VideoTaskRequest();
		request.setIdTask(task.getId());
		request.setDescription(task.getDescription());
		request.setLink(Utilities.parseLinkYouTube(task.getLink()));
		request.setTitle(task.getTitle());
		return request;
		
	}
	
	public static VideoTaskRequest taskToRequestEditProfile(VideoYouTubeTask task) {
		VideoTaskRequest request = new VideoTaskRequest();
		request.setIdTask(task.getId());
		request.setDescription(task.getDescription());
		request.setLink(task.getLink());
		request.setTitle(task.getTitle());
		return request;
		
	}
	
	public static List<VideoTaskRequest> taskListToRequestList(List<VideoYouTubeTask> list) {
		
		List<VideoTaskRequest> list2 = new ArrayList<>();
		for(VideoYouTubeTask t: list) {
			VideoTaskRequest request = VideoYouTubeTaskMapper.taskToRequestViewProfile(t);
			list2.add(request);
		}
		
		return list2;
	}
	

}
