package ua.karatnyk.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ua.karatnyk.domain.StudentFilter;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;

public interface StudentService {
	
	List<UserEntity> findAllActiveStudents();
	
	Page<UserEntity> getPagesStudents(int pageNumber, int pageSize, String sort, String sortByField);
	Page<UserEntity> getPagesStudentsWithFilter(int pageNumber, int pageSize, String sort, String sortByField, StudentFilter filter);
	List<UserEntity> getStudentsWithFilter(StudentFilter filter);
	Page<UserEntity> getPagesStudentsWithoutGroup(int pageNumber, int pageSize, String sort, String sortByField, Group group);
	List<UserEntity> getStudentsWithoutGroup(Group group);
	Page<UserEntity> getPagesStudentsWithGroup(int pageNumber, int pageSize, String sort, String sortByField, Group group);
	Page<UserEntity> getPagesStudentsWithoutGroupWithFilter(int pageNumber, int pageSize, String sort, String sortByField,Group group, StudentFilter filter);
	List<UserEntity> getStudentsWithoutGroupWithFilter(Group group, StudentFilter filter);
	Page<UserEntity> getPagesStudentsWithGroupWithFilter(int pageNumber, int pageSize, String sort, String sortByField,Group group, StudentFilter filter);
	List<UserEntity> getStudentsWithGroupWithFilter(Group group, StudentFilter filter);
	
}
