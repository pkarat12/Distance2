package ua.karatnyk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.Subject;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity>{
	
	@Query("SELECT u FROM UserEntity u WHERE u.login = :login and u.isDeleted = false")
	UserEntity findUserByLoginAndActice(@Param("login") String login);
	
	@Query("SELECT u FROM UserEntity u WHERE u.login = :login")
	UserEntity findUserByLogin(@Param("login") String login);
	
	@Query("SELECT u FROM UserEntity u WHERE u.id = :id and u.isDeleted = false")
	UserEntity findUserByIdAndActice(@Param("id") int id);
	
	@Query("SELECT u FROM UserEntity u WHERE u.role = :role and u.isDeleted = false")
	List<UserEntity> findUsersByRoleAndActice(@Param("role") Role role);
	
	@Query("SELECT u FROM UserEntity u WHERE u.role = :role and u.isDeleted = false")
	Page<UserEntity> findUsersByRoleAndActice(@Param("role") Role role, Pageable pageable);
	
	@Modifying
	@Query("update UserEntity u set u.password = :password, u.passwordText = :passwordText, "
			+ "u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("passwordText") String passwordText, @Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);
	
	@Modifying
	@Query("update UserEntity u set u.email = :email, u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void updateEmail(@Param("email") String email, @Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);
	
	@Modifying
	@Query("update UserEntity u set u.nameFoto = :nameFoto, u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void updateFoto(@Param("nameFoto") String nameFoto, @Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);
	
	@Modifying
	@Query("update UserEntity u set "
			+ "u.lastName = :lastName, u.firstName = :firstName, "
			+ "u.middleName = :middleName, u.email = :email,"
			+ " u.birthDate = :birthDate, u.nameFoto = :nameFoto, "
			+ "u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser "
			+ "where u.id = :id")
	void updateUser(@Param("lastName") String lastName, @Param("firstName") String firstName, 
			@Param("middleName") String middleName, @Param("email") String email,
			@Param("birthDate") Date birthDate, @Param("nameFoto") String nameFoto, 
			@Param("lastUpdatedAt") Date lastUpdatedAt, @Param("lastUpdateByUser") UserEntity lastUpdateByUser,
			@Param("id") int id);
	
	
	@Modifying
	@Query("update UserEntity u set "
			+ "u.lastName = :lastName, u.firstName = :firstName, "
			+ "u.middleName = :middleName, u.email = :email,"
			+ " u.birthDate = :birthDate, u.nameFoto = :nameFoto, "
			+ "u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser, u.subject = :subject, u.classStudent = :classStudent, u.login = :login "
			+ "where u.id = :id")
	void updateUserByDirector(@Param("lastName") String lastName, @Param("firstName") String firstName, 
			@Param("middleName") String middleName, @Param("email") String email,
			@Param("birthDate") Date birthDate, @Param("nameFoto") String nameFoto, 
			@Param("lastUpdatedAt") Date lastUpdatedAt, @Param("lastUpdateByUser") UserEntity lastUpdateByUser, 
			@Param("subject") Subject subject, @Param("classStudent") ClassToStudent classStudent, @Param("login") String login,
			@Param("id") int id);
	
	
	@Modifying
	@Query("update UserEntity u set u.isDeleted = true, u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void deleteUser(@Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);

}
