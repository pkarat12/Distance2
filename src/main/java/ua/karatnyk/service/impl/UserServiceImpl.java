package ua.karatnyk.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.repository.UserRepository;
import ua.karatnyk.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity findByIdActive(int id) {
		return userRepository.findUserByIdAndActice(id);
	}

	@Override
	public UserEntity findByLoginActive(String login) {
		return userRepository.findUserByLoginAndActice(login);
	}

	@Override
	@Transactional
	public void saveUser(UserEntity entity) {
		String password = entity.getPassword();
		entity.setPassword(passwordEncoder.encode(password));
		userRepository.save(entity);
		
	}

	@Override
	@Transactional
	public void updatePassword(String password, String passwordText, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		userRepository.updatePassword(passwordEncoder.encode(password), passwordText, lastUpdatedAt, lastUpdateByUser, id);
	}

	@Override
	@Transactional
	public void updateEmail(String email, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		userRepository.updateEmail(email, lastUpdatedAt, lastUpdateByUser, id);
		
	}

	@Override
	@Transactional
	public void updateUser(String lastName, String firstName, String middleName, String email, Date birthDate,
			String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		userRepository.updateUser(lastName, firstName, middleName, email, birthDate, nameFoto, lastUpdatedAt, lastUpdateByUser,  id);
		
	}

	@Override
	@Transactional
	public void updateUserByDirector(String lastName, String firstName, String middleName, String email, Date birthDate,
			String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, Subject subject,
			ClassToStudent classStudent, String login, int id) {
		userRepository.updateUserByDirector(lastName, firstName, middleName, email, birthDate, nameFoto, lastUpdatedAt, lastUpdateByUser, subject, classStudent, login, id);
		
	}

	@Override
	@Transactional
	public void deleteUser(Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		userRepository.deleteUser(lastUpdatedAt, lastUpdateByUser, id);
		
	}

	@Override
	public UserEntity findById(int id) {
		return userRepository.findUserByIdAndActice(id);
	}

	@Override
	public UserEntity findByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

	@Override
	@Transactional
	public void updateFoto(String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		userRepository.updateFoto(nameFoto, lastUpdatedAt, lastUpdateByUser, id);
		
	}
}
