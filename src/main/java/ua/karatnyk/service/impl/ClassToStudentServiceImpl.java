package ua.karatnyk.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.karatnyk.domain.ClassFilter;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;
import ua.karatnyk.repository.ClassToStudentRepository;
import ua.karatnyk.service.ClassToStudentService;

@Service
public class ClassToStudentServiceImpl implements ClassToStudentService{
	
	@Autowired
	private ClassToStudentRepository classRepository;

	@Override
	public List<ClassToStudent> findAllActiveClasses() {
		return classRepository.findAllClassToStudentsActived();
	}

	@Override
	public Page<ClassToStudent> getPagesClasses(int pageNumber, int pageSize, String sort, String sortByField) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return classRepository.findAllClassToStudentActived(request);
	}

	@Override
	@Transactional
	public void saveClassToStudent(ClassToStudent classToStudent) {
		classRepository.save(classToStudent);
		
	}

	@Override
	public ClassToStudent findClass(UserEntity userClassTeacher) {
		return classRepository.findClass(userClassTeacher);
	}

	/*@Override
	@Transactional
	public void updateClassToStudent(ClassToStudent classToStudent) {
		classRepository.save(classToStudent);
		
	}*/


	@Override
	public ClassToStudent findClass(int idClass) {
		return classRepository.findOne(idClass);
	}

	@Override
	public ClassToStudent findActiveClass(LetterToGrade letter, Grade grade) {
		return classRepository.findActiveClass(letter, grade);
	}

	@Override
	public Page<ClassToStudent> getPagesClassesWithFilter(int pageNumber, int pageSize, String sort, String sortByField,
			ClassFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return classRepository.findAll(getSpecification(filter), request);
	}

	private Specification<ClassToStudent> getSpecification(ClassFilter filter) {
		return new Specification<ClassToStudent>() {

			@Override
			public Predicate toPredicate(Root<ClassToStudent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("grade"), filter.getGrade());
				return cb.and(predicate1, predicate2);
			}
		};
	}

	@Override
	@Transactional
	public void updateUserClassTeacher(UserEntity userClassTeacher, Date lastUpdatedAt, UserEntity lastUpdateByUser,
			int id) {
		
		classRepository.updateUserClassTeacher(userClassTeacher, lastUpdatedAt, lastUpdateByUser, id);
	}

	@Override
	@Transactional
	public void delete(UserEntity currentUser, Date lastUpdatedAt, int id) {
		classRepository.update(currentUser, lastUpdatedAt, id);
		
	}

	@Override
	public List<ClassToStudent> getClassesWithFilter(ClassFilter filter) {
		return classRepository.findAll(getSpecification(filter));
	}


}
