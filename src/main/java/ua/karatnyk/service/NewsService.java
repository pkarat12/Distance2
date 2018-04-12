package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import ua.karatnyk.domain.NewsTitleFilter;
import ua.karatnyk.entity.News;
import ua.karatnyk.entity.UserEntity;

public interface NewsService {
	
	List<News> findAllNewsNotDeleted();
	
	List<News> findAllNewsNotDeleted(UserEntity user);
	
	void saveNews(News news);
	
	News findById(int id);
	News findById(int id, UserEntity user);
	
	
	Page<News> getPagebleNews(int pageNumber, int pageSize, String sort, String sortByField);
	Page<News> getPagebleNews(int pageNumber, int pageSize, String sort, String sortByField, UserEntity user);
	
	Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField, String titleFilter);
	
	Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField, NewsTitleFilter titleFilter);
	List<News> getNewsWithTitleFilter(NewsTitleFilter titleFilter);
	Page<News> getPagebleNewsWithTitleFilter(int pageNumber, int pageSize, String sort, String sortByField, NewsTitleFilter titleFilter, UserEntity user);
	List<News> getNewsWithTitleFilter(NewsTitleFilter titleFilter, UserEntity user);
	
	void deleteNewsById(int id);
	
	void updateFoto(String nameFoto, Date lastUpdatedAt, UserEntity lastUpdateByUser, int id);
	
}
