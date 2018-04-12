package ua.karatnyk.mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import ua.karatnyk.domain.EmailRequest;
import ua.karatnyk.domain.ProfileUserEditRequest;
import ua.karatnyk.domain.ProfileUserViewRequest;
import ua.karatnyk.domain.UserEditRequest;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.FileManager;

public interface UserMapper {
	
	public static User toSecurityUser(UserEntity entity) {
		return new User(entity.getLogin(), entity.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole())));
	}
	
public static ProfileUserViewRequest userEntityToProfileUserViewRequest(UserEntity entity) {
		
		ProfileUserViewRequest request = new ProfileUserViewRequest();
		request.setId(entity.getId());
		if(entity.getNameFoto().equals(Constants.USERS_NO_AVATAR))
			request.setEncodedToByteByNameFoto(FileManager.encodedFileToByteFromProject(FileManager.pathToDefaultImage("noAvatar.png", Constants.FOLDER_FOR_USERS_PHOTOS)));
		else 
			request.setEncodedToByteByNameFoto(FileManager.encodedFileToByteFromProject(FileManager.fullPathToUserImages(entity.getId(), entity.getNameFoto(), Constants.FOLDER_FOR_USERS_PHOTOS)));
		if(entity.getBirthDate() != null) {
			SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATE_DATE);
			request.setBirthDate(format.format(entity.getBirthDate()));
		}
		request.setEmail(entity.getEmail());
		if(entity.getClassTeacher() != null)
			request.setClassTeach(entity.getClassTeacher().toString());
		request.setFirstName(entity.getFirstName());
		request.setMiddleName(entity.getMiddleName());
		request.setLastName(entity.getLastName());
		if(entity.getSubject() != null)
			request.setSubject(entity.getSubject().getTitle());
		request.setNumberSchool(entity.getNumberSchool().getTitle());
		request.setLogin(entity.getLogin());
		if(entity.getClassStudent() != null)
			request.setClassInStudy(entity.getClassStudent().toString());
		//request.setClassTeacherFullName();
		return request;
	}
	
	public static ProfileUserEditRequest userEntityToProfileUserEditRequest(UserEntity entity) {
		ProfileUserEditRequest request = new ProfileUserEditRequest();
		request.setId(entity.getId());
		request.setNameFoto(entity.getNameFoto());
		request.setFirstName(entity.getFirstName());
		request.setLastName(entity.getLastName());
		request.setMiddleName(entity.getMiddleName());
		request.setEmail(entity.getEmail());
		if(entity.getBirthDate() != null) {
			SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATE_DATE);
			request.setBirthDate(format.format(entity.getBirthDate()));
		} 
		
		return request;
	}
	
	public static UserEntity profileUserEditRequestToUserEntity(ProfileUserEditRequest request, UserEntity currentUser) {
		UserEntity entity = new UserEntity();
		
		entity.setId(request.getId());
		if(!request.getFile().isEmpty()) {
			entity.setNameFoto(FileManager.nameFile(request.getFile()));
			try {
				FileManager.savePhotoUserInProject(request.getFile(), request.getId(), Constants.FOLDER_FOR_USERS_PHOTOS);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			entity.setNameFoto(request.getNameFoto());
		}
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setMiddleName(request.getMiddleName());
		entity.setEmail(request.getEmail());
		if(!request.getBirthDate().isEmpty() && request.getBirthDate() != null) {
			SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATE_DATE);
			try {
				entity.setBirthDate(format.parse(request.getBirthDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		entity.setLastUpdateByUser(currentUser);
		return entity;
	}
	
	public static EmailRequest userEntityToEmailRequest(UserEntity entity) {
		EmailRequest request = new EmailRequest();
		request.setEmail(entity.getEmail());
		request.setId(entity.getId());
		return request;
	}
	
	public static UserEditRequest userEntetyToUserEditRequest(UserEntity entity) {
		UserEditRequest request = new UserEditRequest();
		request.setId(entity.getId());
		request.setNameFoto(entity.getNameFoto());
		request.setFirstName(entity.getFirstName());
		request.setLastName(entity.getLastName());
		request.setMiddleName(entity.getMiddleName());
		request.setEmail(entity.getEmail());
		if(entity.getBirthDate() != null) {
			SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATE_DATE);
			request.setBirthDate(format.format(entity.getBirthDate()));
		} 
		request.setSubject(entity.getSubject());
		request.setRole(entity.getRole());
		request.setOldLogin(entity.getLogin());
		request.setNewLogin(entity.getLogin());
		request.setClassStudent(entity.getClassStudent());
		return request;
	}
	
	public static UserEntity userEditRequestToUserEntity(UserEditRequest request, UserEntity currentUser) {
		UserEntity entity = new UserEntity();
		entity.setId(request.getId());
		if(!request.getFile().isEmpty()) {
			entity.setNameFoto(FileManager.nameFile(request.getFile()));
			try {
				FileManager.savePhotoUserInProject(request.getFile(), request.getId(), Constants.FOLDER_FOR_USERS_PHOTOS);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			entity.setNameFoto(request.getNameFoto());
		}
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setMiddleName(request.getMiddleName());
		entity.setEmail(request.getEmail());
		if(!request.getBirthDate().isEmpty() && request.getBirthDate() != null) {
			SimpleDateFormat format = new SimpleDateFormat(Constants.FORMATE_DATE);
			try {
				entity.setBirthDate(format.parse(request.getBirthDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		entity.setSubject(request.getSubject());
		entity.setLogin(request.getNewLogin());
		entity.setClassStudent(request.getClassStudent());
		entity.setLastUpdateByUser(currentUser);
		return entity;
	}

}
