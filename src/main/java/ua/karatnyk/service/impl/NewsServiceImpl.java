package ua.karatnyk.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.karatnyk.domain.NewsTitleFilter;
import ua.karatnyk.entity.News;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.repository.NewsRepository;
import ua.karatnyk.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public void saveNews(News news) {
		newsRepository.save(news);
		
	}

	@Override
	public News findById(int id) {
		return newsRepository.findOne(id);
	}

	@Override
	public Page<News> getPagebleNews(int pageNumber, int pageSize, String sort, String sortByField) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return newsRepository.findAllNewsActived(request);
	}

	@Override
	public void deleteNewsById(int id) {
		News news = newsRepository.findOne(id);
		news.setDeleted(true);
		newsRepository.save(news);
		
	}

	@Override
	public List<News> findAllNewsNotDeleted() {
		return newsRepository.findAllNewsActived();
	}

	@Override
	public Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField,
			String titleFilter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return newsRepository.findAllNewsActivedWithTitleFilter(request, titleFilter);
	}

	@Override
	public Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField,
			NewsTitleFilter titleFilter) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return newsRepository.findAll(getSpecification(titleFilter), request);
	}
	
	private Specification<News> getSpecification(NewsTitleFilter filter) {
		return new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Expression<Boolean> status = root.get("isDeleted");
				Predicate predicate1 = builder.isFalse(status);
				Predicate predicate2 = builder.like(root.get("title"), "%" + filter.getSearch() +"%");
				return builder.and(predicate1, predicate2);
			}
		};
	}

	@Override
	public List<News> findAllNewsNotDeleted(UserEntity user) {
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				return cb.and(predicate1, predicate2);
			}
		});
	}

	@Override
	public News findById(int id, UserEntity user) {
		return newsRepository.findOne(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.equal(root.get("id"), id);
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

	@Override
	public Page<News> getPagebleNews(int pageNumber, int pageSize, String sort, String sortByField, UserEntity user) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				return cb.and(predicate1, predicate2);
			}
		}, request);
	}

	@Override
	public Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField,
			NewsTitleFilter titleFilter, UserEntity user) {
		PageRequest request = new PageRequest(pageNumber-1, pageSize, sort.toUpperCase().equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, sortByField);
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.like(root.get("title"), "%" + titleFilter.getSearch() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		}, request);
	}

	@Override
	@Transactional
	public void updateFoto(String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id) {
		newsRepository.updateFoto(nameFoto, lastUpdatedAt, lastUpdateByUser, id);
		
	}

	@Override
	public List<News> getNewsWithTitleFilter(NewsTitleFilter titleFilter) {
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate3 = cb.like(root.get("title"), "%" + titleFilter.getSearch() +"%");
				return cb.and(predicate1, predicate3);
			}
		});
	}

	@Override
	public List<News> getNewsWithTitleFilter(NewsTitleFilter titleFilter, UserEntity user) {
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.isFalse(root.get("isDeleted"));
				Predicate predicate2 = cb.equal(root.get("createdByUser"), user);
				Predicate predicate3 = cb.like(root.get("title"), "%" + titleFilter.getSearch() +"%");
				return cb.and(predicate1, predicate2, predicate3);
			}
		});
	}

}
