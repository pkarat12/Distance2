package ua.karatnyk.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.StudentAddRequest;
import ua.karatnyk.domain.StudentViewRequest;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.FileManager;

public class StudentMapper {
	
	public static StudentViewRequest userEntityToStudentViewRequest(UserEntity entity) {
		StudentViewRequest request = new StudentViewRequest();
		if(entity.getBirthDate() != null) //
			request.setBirthdate(new SimpleDateFormat(Constants.FORMATE_DATE).format(entity.getBirthDate())); //
		if(entity.getClassStudent() != null)
			request.setClassStudent(entity.getClassStudent().toString());
		request.setEmail(entity.getEmail());
		request.setFirstName(entity.getFirstName()); 
		
		if(entity.getNameFoto().equals(Constants.USERS_NO_AVATAR)) {
			request.setFotoInBydeCode(FileManager.encodedFileToByteFromProject(FileManager.pathToDefaultImage(Constants.USERS_NO_AVATAR, Constants.FOLDER_FOR_USERS_PHOTOS)));
		} else {
			request.setFotoInBydeCode(FileManager.encodedFileToByteFromProject(FileManager.fullPathToUserImages(entity.getId(), entity.getNameFoto(), Constants.FOLDER_FOR_USERS_PHOTOS)));
		}
		
		request.setId(entity.getId());
		request.setLastName(entity.getLastName());
		request.setLogin(entity.getLogin());
		request.setMiddleName(entity.getMiddleName());
		if(entity.isDeleted()) {
			request.setStatus("не навчається");
		} else {
			request.setStatus("навчається");
		}
		return request;
	}
	public static List<StudentViewRequest> listUserEntityToListStudentRequests(List<UserEntity> entities) {
		List<StudentViewRequest> requests = new ArrayList<>();
		for(UserEntity e: entities) {
			StudentViewRequest request = StudentMapper.userEntityToStudentViewRequest(e);
			requests.add(request);
		}
		
		return requests;
	}
	
	public static UserEntity studentAddRequestToUserEntity(StudentAddRequest request, UserEntity currentEntity) {
		UserEntity entity = new UserEntity();
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setMiddleName(request.getMiddleName());
		entity.setLogin(request.getLogin());
		entity.setPassword(request.getPassword());
		entity.setPasswordText(request.getPassword());
		entity.setClassStudent(request.getClassStudent());
		entity.setCreatedByUser(currentEntity);
		entity.setNameFoto(Constants.USERS_NO_AVATAR);
		entity.setLastUpdateByUser(currentEntity);
		entity.setNumberSchool(currentEntity.getNumberSchool());
		entity.setRole(Role.ROLE_STUDENT);
		return entity;
	}

}
