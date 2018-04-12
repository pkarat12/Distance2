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

import ua.karatnyk.domain.LessonFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.repository.LessonRepository;
import ua.karatnyk.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{
	
	@Autowired
	private LessonRepository repository;

	@Override
	public List<Lesson> findAllNotDeletedByUserByCourse(UserEntity entity, Course course) {
		return repository.findAll(new Specification<Lesson>() {
			
			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), entity);
				Predicate predicate3 = cb.equal(root.get("course"), course);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	public Page<Lesson> findAllPagesNotDeletedByUserByCourse(int pageNumber, int pageSize, String sort,
			String sortByField, UserEntity entity, Course course) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), entity);
				Predicate predicate3 = cb.equal(root.get("course"), course);
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public Page<Lesson> findAllPagesNotDeletedByUserByCourseWithFilter(int pageNumber, int pageSize, String sort,
			String sortByField, UserEntity entity, Course course, LessonFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), entity);
				Predicate predicate3 = cb.equal(root.get("course"), course);
				Predicate predicate4 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		}, request);
	}

	@Override
	public void save(Lesson lesson) {
		repository.save(lesson);
		
	}

	@Override
	public Lesson findByIdNotDeletedByUser(UserEntity user, int id) {
		return repository.findOne(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	@Transactional
	public void changeStatus(Boolean visibility, UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(visibility, currentUser, lastUpdatedAt, id);
	}

	@Override
	@Transactional
	public void delete(UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void update(String title, String description, String number, UserEntity currentUser, Date lastUpdatedAt,
			int id) {
		repository.update(title, description, number, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	public Page<Lesson> find(int pageNumber, int pageSize, String sort, String sortByField, Course course) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isTrue(root.get("visibility"));
				Predicate predicate3 = cb.equal(root.get("course"), course);
				return cb.and(predicate1, predicate2, predicate3);
			}
			
		}, request);
	}

	@Override
	public Lesson find(Course course, int id) {
		return repository.findOne(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isTrue(root.get("visibility"));
				Predicate predicate3 = cb.equal(root.get("course"), course);
				Predicate predicate4 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3, predicate4);
			}
		});
	}

	@Override
	public Lesson find(int id) {
		return repository.findOne(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2);
			}
		});
	}

	@Override
	public List<Lesson> find(Course course, String search) {
		return repository.findAll(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isTrue(root.get("visibility"));
				Predicate predicate3 = cb.equal(root.get("course"), course);
				Predicate predicate5 = cb.like(root.get("number"), "%" + search +"%");
				Predicate predicate4 = cb.like(root.get("title"), "%" + search +"%");
				return cb.and(predicate1, predicate2, predicate3, cb.or(predicate4, predicate5));
			}
		});
	}

	@Override
	public List<Lesson> findAllNotDeletedByUserByCourseWithFilter(UserEntity entity, Course course,
			LessonFilter filter) {
		return repository.findAll(new Specification<Lesson>() {

			@Override
			public Predicate toPredicate(Root<Lesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), entity);
				Predicate predicate3 = cb.equal(root.get("course"), course);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	



}
