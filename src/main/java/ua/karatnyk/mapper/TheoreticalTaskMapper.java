package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.TheoreticalTaskRequest;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.FileManager;

public class TheoreticalTaskMapper {
	
	
	public static TheoreticalTask taskRequestToTaskForSave(TheoreticalTaskRequest request, UserEntity currentUser) {
		TheoreticalTask task = new TheoreticalTask();
		
		task.setCreatedByUser(currentUser);
		task.setLastUpdateByUser(currentUser);
		task.setDescription(request.getDescription());
		if(!request.getFile().isEmpty()) {
			task.setFileName(FileManager.nameFile(request.getFile()));
			FileManager.saveFileInProject(request.getFile(), currentUser.getId(), Constants.FOLDER_FOR_USERS_FILES);		
		}
		task.setTitle(request.getTitle());
		return task;
		
	}
	
	public static TheoreticalTaskRequest taskToRequestViewProfile(TheoreticalTask task) {
		TheoreticalTaskRequest request = new TheoreticalTaskRequest();
		
		request.setIdTask(task.getId());
		request.setDescription(task.getDescription());
		if(task.getFileName() != null && !task.getFileName().isEmpty()) {
			request.setNameFile(task.getFileName());
		}
		request.setTitle(task.getTitle());
		request.setUserId(task.getCreatedByUser().getId());
		return request;
		
	}
	
	public static List<TheoreticalTaskRequest> taskListToRequestList(List<TheoreticalTask> list) {
		
		List<TheoreticalTaskRequest> list2 = new ArrayList<>();
		for(TheoreticalTask t: list) {
			TheoreticalTaskRequest request = TheoreticalTaskMapper.taskToRequestViewProfile(t);
			list2.add(request);
		}
		
		return list2;
	}
	

}
