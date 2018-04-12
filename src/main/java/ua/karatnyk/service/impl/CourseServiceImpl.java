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

import ua.karatnyk.domain.CourseFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.repository.CourseRepository;
import ua.karatnyk.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository repository;

	@Override
	public List<Course> findAllNotDeletedByUser(UserEntity entity) {
		return repository.findAllNotDeletedByUser(entity);
	}

	@Override
	public Page<Course> getPagesCoursesByUser(int pageNumber, int pageSize, String sort, String sortByField,
			UserEntity userEntity) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAllNotDeletedByUser(request, userEntity);
	}

	@Override
	public Page<Course> getPagesCoursesWithFilter(int pageNumber, int pageSize, String sort, String sortByField,
			CourseFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUserEntity());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				if(filter.getGrade() != null) {
					Predicate predicate4 = cb.equal(root.get("grade"), filter.getGrade());
					return cb.and(predicate1, predicate2, predicate3, predicate4);
				}
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public void save(Course course) {
		repository.save(course);
		
	}

	@Override
	public Course findCourseByIdNotDeletedByUser(int id, UserEntity user) {
		return repository.findOne(new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	@Transactional
	public void update(String title, String description, Grade grade, UserEntity currentUser, Date lastUpdatedAt,
			int id) {
		repository.update(title, description, grade, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void delete(UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(currentUser, lastUpdatedAt, id);
	}

	@Override
	public Course findCourseByIdNotDeleted(int id) {
		return repository.findOne(new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2);
			}
		});
	}

	@Override
	public List<Course> getCoursesWithFilter(CourseFilter filter) {
		return repository.findAll(new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUserEntity());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				if(filter.getGrade() != null) {
					Predicate predicate4 = cb.equal(root.get("grade"), filter.getGrade());
					return cb.and(predicate1, predicate2, predicate3, predicate4);
				}
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	
}
