package ua.karatnyk.service;

import java.util.Date;

import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Subject;

public interface UserService {
	
	UserEntity findById(int id);
	
	UserEntity findByIdActive(int id);
	
	UserEntity findByLogin(String login);
	
	UserEntity findByLoginActive(String login);
	
	
	void saveUser(UserEntity entity);
	void updateUser(String lastName,  String firstName, 
			String middleName, String email,
			 Date birthDate, String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id);
	
	void updatePassword(String password, String passwordText, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id);
	void updateEmail(String email, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id);
	
	void updateUserByDirector(String lastName, String firstName, 
			 String middleName, String email,
			 Date birthDate, String nameFoto, 
			 Date lastUpdatedAt, UserEntity lastUpdateByUser, 
			 Subject subject, ClassToStudent classStudent, String login,
			 int id);
	
	void deleteUser(Date lastUpdatedAt, UserEntity lastUpdateByUser, int id);
	
	void updateFoto(String nameFoto, Date lastUpdatedAt, 
		 UserEntity lastUpdateByUser, int id);
}
