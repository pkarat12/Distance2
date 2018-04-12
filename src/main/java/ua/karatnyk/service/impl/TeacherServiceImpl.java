package ua.karatnyk.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.karatnyk.domain.TeacherFilter;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.repository.UserRepository;
import ua.karatnyk.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Page<UserEntity> getPagesTeachers(int pageNumber, int pageSize, String sort, String sortByField) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findUsersByRoleAndActice(Role.ROLE_TEACHER, request);
	}


	@Override
	public List<UserEntity> findAllActiveTeachers() {
		return userRepository.findUsersByRoleAndActice(Role.ROLE_TEACHER);
	}


	@Override
	public Page<UserEntity> getPagesTeachersWithFilter(int pageNumber, int pageSize, String sort, String sortByField,
			TeacherFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(getSpecification(filter), request);
	}
	
	
	private Specification<UserEntity> getSpecification(TeacherFilter filter) {
		return new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), filter.getRole());
				Expression<String> expression1 = root.get("lastName");
				Expression<String> expression2 = root.get("firstName");
				Expression<String> expression3 = root.get("middleName");
				Expression<String> expression=cb.lower( cb.concat(cb.concat(cb.concat(expression1, " "), cb.concat(expression2, " ")), expression3));
				Predicate predicate3 = cb.like(expression, "%" + filter.getSearch() +"%");
				if(filter.getSubject() != null) {
					Predicate predicate4 = cb.equal(root.get("subject"), filter.getSubject());
					return cb.and(predicate1, predicate2, predicate3, predicate4);
				}
				return cb.and(predicate1, predicate2, predicate3);
			}
		};
	}


	@Override
	public List<UserEntity> getPagesTeachersWithFilter(TeacherFilter filter) {
		return userRepository.findAll(getSpecification(filter));
	}
	


}
