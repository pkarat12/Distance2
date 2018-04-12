package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.List;

import ua.karatnyk.domain.ClassAddRequest;
import ua.karatnyk.domain.ClassEditRequest;
import ua.karatnyk.domain.ClassViewRequest;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;

public class ClassMapper {
	
	public static ClassToStudent addRequestToClass(ClassAddRequest request, UserEntity currentUser) {
		ClassToStudent classToStudent = new ClassToStudent();
		classToStudent.setCreatedByUser(currentUser);
		classToStudent.setGrade(request.getGrade());
		classToStudent.setLastUpdateByUser(currentUser);
		classToStudent.setLetterToGrade(request.getLetterToGrade());
		classToStudent.setUserClassTeacher(request.getUserClassTeacher());
		return classToStudent;
	}
	
	public static ClassViewRequest classEntityToViewRequest(ClassToStudent entity) {
		ClassViewRequest request = new ClassViewRequest();
		request.setId(entity.getId());
		request.setTitleClass(entity.toString());
		if(entity.getUserClassTeacher() != null)
			request.setClassTeacher(entity.getUserClassTeacher().getFirstName()+" "+entity.getUserClassTeacher().getMiddleName()
					+" "+entity.getUserClassTeacher().getLastName());
		request.setListStudentsInClass(entity.getUserEntities());
		return request;
	}
	
	public static List<ClassViewRequest> listClassEntityToListClassViewRequest(List<ClassToStudent> list) {
		List<ClassViewRequest> requests = new ArrayList<>();
		for (ClassToStudent classToStudent : list) {
			ClassViewRequest request = ClassMapper.classEntityToViewRequest(classToStudent);
			requests.add(request);
		}
		return requests;
	}
	
	public static ClassToStudent classAddRequestToClassToStudent(ClassAddRequest request, UserEntity currentUser) {
		ClassToStudent classToStudent = new ClassToStudent();
		classToStudent.setCreatedByUser(currentUser);
		classToStudent.setGrade(request.getGrade());
		classToStudent.setLastUpdateByUser(currentUser);
		classToStudent.setLetterToGrade(request.getLetterToGrade());
		classToStudent.setUserClassTeacher(request.getUserClassTeacher());
		return classToStudent;
	}
	
	public static ClassEditRequest classToStudentToClassEditRequest(ClassToStudent entity) {
		ClassEditRequest request = new ClassEditRequest();
		request.setId(entity.getId());
		request.setUserClassTeacher(entity.getUserClassTeacher());
		return request;
	}
	
	public static ClassToStudent classEditRequestToClassToStudent(ClassEditRequest request) {
		ClassToStudent entity = new ClassToStudent();
		entity.setId(request.getId());
		entity.setUserClassTeacher(request.getUserClassTeacher());
		return entity;
	}

}
