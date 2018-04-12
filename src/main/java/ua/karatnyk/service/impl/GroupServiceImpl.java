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

import ua.karatnyk.domain.GroupFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.repository.GroupRepository;
import ua.karatnyk.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Group> findAllNotDeletedByUser(UserEntity entity) {
		return groupRepository.findAll(new Specification<Group>() {
			
			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), entity);
				return cb.and(predicate1, predicate2);
			}
		});
	}

	@Override
	public Page<Group> getPagesGroupsByUser(int pageNumber, int pageSize, String sort, String sortByField,
			UserEntity userEntity) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return groupRepository.findAll(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), userEntity);
				return cb.and(predicate1, predicate2);
			}
		}, request);
	}

	@Override
	public Page<Group> getPagesGroupsWithFilter(int pageNumber, int pageSize, String sort, String sortByField,
			GroupFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return groupRepository.findAll(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUser());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public void save(Group group) {
		groupRepository.save(group);
		
	}

	@Override
	public Group findGroupByIdNotDeletedByUser(int id, UserEntity user) {
		return groupRepository.findOne(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	@Transactional
	public void update(String title, Course course, UserEntity currentUser, Date lastUpdatedAt, int id) {
		groupRepository.update(title, course, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void delete(UserEntity currentUser, Date lastUpdatedAt, int id) {
		groupRepository.update(currentUser, lastUpdatedAt, id);
		
	}

	@Override
	public List<Group> findWhithUserNotDeleted(UserEntity user) {
		return groupRepository.findAll(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isMember(user, root.get("users"));
				return cb.and(predicate1, predicate2);
			}
		});
	}

	@Override
	public List<Group> getGroupsWithFilter(GroupFilter filter) {
		return groupRepository.findAll(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUser());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

}
