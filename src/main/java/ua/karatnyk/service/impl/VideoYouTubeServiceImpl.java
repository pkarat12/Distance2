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

import ua.karatnyk.domain.VideoYouTubeTaskFilter;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.repository.VideoYouTubeRepository;
import ua.karatnyk.service.VideoYouTubeService;

@Service
public class VideoYouTubeServiceImpl implements VideoYouTubeService{
	
	@Autowired
	private VideoYouTubeRepository repository;

	@Override
	public List<VideoYouTubeTask> findAllNotDeletedByUser(UserEntity entity) {
		return repository.findAllNotDeletedByUser(entity);
	}

	@Override
	public Page<VideoYouTubeTask> getPagesVideoYouTubeTasksByUser(int pageNumber, int pageSize, String sort,
			String sortByField, UserEntity userEntity) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAllNotDeletedByUser(request, userEntity);
	}

	@Override
	public Page<VideoYouTubeTask> getPagesVideoYouTubeTasksWithFilter(int pageNumber, int pageSize, String sort,
			String sortByField, VideoYouTubeTaskFilter filter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return repository.findAll(new Specification<VideoYouTubeTask>() {

			@Override
			public Predicate toPredicate(Root<VideoYouTubeTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUserEntity());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	public void save(VideoYouTubeTask task) {
		repository.save(task);
		
	}

	@Override
	public VideoYouTubeTask findTaskByIdNotDeleted(int id, UserEntity userEntity) {
		return repository.findOne(new Specification<VideoYouTubeTask>() {

			@Override
			public Predicate toPredicate(Root<VideoYouTubeTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("id"), id);
				Predicate predicate3 = cb.equal(root.get("createdByUser"), userEntity);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	@Transactional
	public void update(String title, String description, String link, UserEntity currentUser, Date lastUpdatedAt,
			int id) {
		repository.update(title, description, link, currentUser, lastUpdatedAt, id);
		
	}

	@Override
	@Transactional
	public void update(UserEntity currentUser, Date lastUpdatedAt, int id) {
		repository.update(currentUser, lastUpdatedAt, id);
	}

	@Override
	public VideoYouTubeTask find(Lesson lesson, int id) {
		return repository.findOne(new Specification<VideoYouTubeTask>() {

			@Override
			public Predicate toPredicate(Root<VideoYouTubeTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.isMember(lesson, root.get("lessons"));
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	public List<VideoYouTubeTask> getVideoYouTubeTasksWithFilter(VideoYouTubeTaskFilter filter) {
		return repository.findAll(new Specification<VideoYouTubeTask>() {

			@Override
			public Predicate toPredicate(Root<VideoYouTubeTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), filter.getUserEntity());
				Predicate predicate3 = cb.like(root.get("title"), "%" + filter.getTitle() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}
}
