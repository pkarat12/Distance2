package ua.karatnyk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.karatnyk.entity.News;
import ua.karatnyk.entity.UserEntity;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News>{
	
	@Query("Select c from News c where c.isDeleted = false")
	Page<News> findAllNewsActived(Pageable pageable);
	
	@Query("Select c from News c where c.isDeleted = false")
	List<News> findAllNewsActived();
	
	@Query("Select c from News c where c.isDeleted = false and c.title like %:filter%")
	Page<News> findAllNewsActivedWithTitleFilter(Pageable pageable, @Param("filter") String filter);
	
	@Modifying
	@Query("update News u set u.nameFoto = :nameFoto, u.lastUpdatedAt = :lastUpdatedAt, u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void updateFoto(@Param("nameFoto") String nameFoto, @Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);
		
}
