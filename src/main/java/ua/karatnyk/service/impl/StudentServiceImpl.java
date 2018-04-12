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

import ua.karatnyk.domain.StudentFilter;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.repository.UserRepository;
import ua.karatnyk.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserEntity> findAllActiveStudents() {
		return userRepository.findUsersByRoleAndActice(Role.ROLE_STUDENT);
	}

	@Override
	public Page<UserEntity> getPagesStudents(int pageNumber, int pageSize, String sort, String sortByField) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findUsersByRoleAndActice(Role.ROLE_STUDENT, request);
	}

	@Override
	public Page<UserEntity> getPagesStudentsWithFilter(int pageNumber, int pageSize, String sort, String sortByField,
			StudentFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(getSpecification(filter), request);
	}
	
	private Specification<UserEntity> getSpecification(StudentFilter filter) {
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
				if(filter.getClassToStudent() != null) {
					Predicate predicate4 = cb.equal(root.get("classStudent"), filter.getClassToStudent());
					return cb.and(predicate1, predicate2, predicate3, predicate4);
				}
				return cb.and(predicate1, predicate2, predicate3);
			}
		};
	}

	@Override
	public Page<UserEntity> getPagesStudentsWithoutGroup(int pageNumber, int pageSize, String sort, String sortByField, Group group) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isNotMember(group, root.get("groups"));
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public Page<UserEntity> getPagesStudentsWithoutGroupWithFilter(int pageNumber, int pageSize, String sort,
			String sortByField, Group group, StudentFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isNotMember(group, root.get("groups"));
				Expression<String> expression1 = root.get("lastName");
				Expression<String> expression2 = root.get("firstName");
				Expression<String> expression3 = root.get("middleName");
				Expression<String> expression=cb.lower( cb.concat(cb.concat(cb.concat(expression1, " "), cb.concat(expression2, " ")), expression3));
				Predicate predicate4 = cb.like(expression, "%" + filter.getSearch() +"%");
				if(filter.getClassToStudent() != null) {
					Predicate predicate5 = cb.equal(root.get("classStudent"), filter.getClassToStudent());
					return cb.and(predicate1, predicate2, predicate3, predicate4, predicate5);
				}
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		}, request);
	}

	@Override
	public Page<UserEntity> getPagesStudentsWithGroup(int pageNumber, int pageSize, String sort, String sortByField,
			Group group) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isMember(group, root.get("groups"));
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public Page<UserEntity> getPagesStudentsWithGroupWithFilter(int pageNumber, int pageSize, String sort,
			String sortByField, Group group, StudentFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isMember(group, root.get("groups"));
				Expression<String> expression1 = root.get("lastName");
				Expression<String> expression2 = root.get("firstName");
				Expression<String> expression3 = root.get("middleName");
				Expression<String> expression=cb.lower( cb.concat(cb.concat(cb.concat(expression1, " "), cb.concat(expression2, " ")), expression3));
				Predicate predicate4 = cb.like(expression, "%" + filter.getSearch() +"%");
				if(filter.getClassToStudent() != null) {
					Predicate predicate5 = cb.equal(root.get("classStudent"), filter.getClassToStudent());
					return cb.and(predicate1, predicate2, predicate3, predicate4, predicate5);
				}
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		}, request);
	}

	@Override
	public List<UserEntity> getStudentsWithFilter(StudentFilter filter) {
		return userRepository.findAll(getSpecification(filter));
	}

	@Override
	public List<UserEntity> getStudentsWithoutGroup(Group group) {
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isNotMember(group, root.get("groups"));
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	public List<UserEntity> getStudentsWithoutGroupWithFilter(Group group, StudentFilter filter) {
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isNotMember(group, root.get("groups"));
				Expression<String> expression1 = root.get("lastName");
				Expression<String> expression2 = root.get("firstName");
				Expression<String> expression3 = root.get("middleName");
				Expression<String> expression=cb.lower( cb.concat(cb.concat(cb.concat(expression1, " "), cb.concat(expression2, " ")), expression3));
				Predicate predicate4 = cb.like(expression, "%" + filter.getSearch() +"%");
				if(filter.getClassToStudent() != null) {
					Predicate predicate5 = cb.equal(root.get("classStudent"), filter.getClassToStudent());
					return cb.and(predicate1, predicate2, predicate3, predicate4, predicate5);
				}
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		});
	}

	@Override
	public List<UserEntity> getStudentsWithGroupWithFilter(Group group, StudentFilter filter) {
		return userRepository.findAll(new Specification<UserEntity>() {

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("role"), Role.ROLE_STUDENT);
				Predicate predicate3 = cb.isMember(group, root.get("groups"));
				Expression<String> expression1 = root.get("lastName");
				Expression<String> expression2 = root.get("firstName");
				Expression<String> expression3 = root.get("middleName");
				Expression<String> expression=cb.lower( cb.concat(cb.concat(cb.concat(expression1, " "), cb.concat(expression2, " ")), expression3));
				Predicate predicate4 = cb.like(expression, "%" + filter.getSearch() +"%");
				if(filter.getClassToStudent() != null) {
					Predicate predicate5 = cb.equal(root.get("classStudent"), filter.getClassToStudent());
					return cb.and(predicate1, predicate2, predicate3, predicate4, predicate5);
				}
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		});
	}

	

}
