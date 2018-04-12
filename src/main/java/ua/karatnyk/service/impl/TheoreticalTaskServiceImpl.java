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

import ua.karatnyk.domain.TheoreticalTasksFilter;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.repository.TheoreticalTaskRepository;
import ua.karatnyk.service.TheoreticalTaskService;

@Service
public class TheoreticalTaskServiceImpl implements TheoreticalTaskService {
	
	@Autowired
	private TheoreticalTaskRepository repository;

	@Override
	public List<TheoreticalTask> findAllNotDeletedByUser(UserEntity entity) {
		return repository.findAllNotDeletedByUser(entity);
	}

	@Override
	public Page<TheoreticalTask> getPagesTheoreticalTasksByUser(int pageNumber, int pageSize, String sort,
			String sortByField, UserEntity userEntity) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAllNotDeletedByUser(request, userEntity);
	}

	@Override
	public Page<TheoreticalTask> getPagesTheoreticalTasksWithFilter(int pageNumber, int pageSize, String sort,
			String sortByField, TheoreticalTasksFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(getSpecification(filter), request);
	}
	
	private Specification<TheoreticalTask> getSpecification(TheoreticalTasksFilter filter) {
		return new Specification<TheoreticalTask>() {

			@Override
			public Predicate toPredicate(Root<TheoreticalTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUserEntity());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		};
	}

	@Override
	public void save(TheoreticalTask task) {
		repository.save(task);
		
	}

	@Override
	public TheoreticalTask findTaskByIdNotDeleted(int id, UserEntity user) {
		return repository.findOne(new Specification<TheoreticalTask>() {

			@Override
			public Predicate toPredicate(Root<TheoreticalTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("id"), id);
				Predicate predicate3 = cb.equal(root.get("createdByUser"), user);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	@Transactional
	public void update(String title, String description, String fileName, UserEntity currentUser, Date lastUpdatedAt,
			int id) {
		repository.update(title, description, fileName, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void update(String title, String description, UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(title, description, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void update(UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(currentUser, lastUpdatedAt, id);
		
	}

	@Override
	public TheoreticalTask find(Lesson lesson, int id) {
		return repository.findOne(new Specification<TheoreticalTask>() {

			@Override
			public Predicate toPredicate(Root<TheoreticalTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isMember(lesson, root.get("lessons"));
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	public List<TheoreticalTask> getTheoreticalTasksWithFilter(TheoreticalTasksFilter filter) {
		return repository.findAll(getSpecification(filter));
	}
}
