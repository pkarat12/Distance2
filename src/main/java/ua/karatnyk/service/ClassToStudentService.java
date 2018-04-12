package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import ua.karatnyk.domain.ClassFilter;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;

public interface ClassToStudentService {
	
	List<ClassToStudent> findAllActiveClasses();
	
	Page<ClassToStudent> getPagesClasses(int pageNumber, int pageSize, String sort, String sortByField);
	
	Page<ClassToStudent> getPagesClassesWithFilter(int pageNumber, int pageSize, String sort, String sortByField, ClassFilter filter);
	List<ClassToStudent> getClassesWithFilter(ClassFilter filter);
	
	void saveClassToStudent(ClassToStudent classToStudent);
	
	ClassToStudent findClass(UserEntity userClassTeacher);
	
	/*void updateClassToStudent(ClassToStudent classToStudent);*/
	
	void updateUserClassTeacher(UserEntity userClassTeacher, Date lastUpdatedAt,  UserEntity lastUpdateByUser, int id);

	ClassToStudent findClass(int idClass);
	
	ClassToStudent findActiveClass(LetterToGrade letter, Grade grade);
	
	void delete(UserEntity currentUser, Date lastUpdatedAt, int id); 

}
