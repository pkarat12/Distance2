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
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;

@Repository
public interface VideoYouTubeRepository extends JpaRepository<VideoYouTubeTask, Integer>, JpaSpecificationExecutor<VideoYouTubeTask>{
	
	@Query("Select c from VideoYouTubeTask c where c.isDeleted = false and c.createdByUser = :user")
	Page<VideoYouTubeTask> findAllNotDeletedByUser(Pageable pageable, @Param("user") UserEntity userEntity);
	
	@Query("Select c from VideoYouTubeTask c where c.isDeleted = false and c.createdByUser = :user")
	List<VideoYouTubeTask> findAllNotDeletedByUser(@Param("user") UserEntity userEntity);
	
	
	@Modifying
	@Query("update VideoYouTubeTask t set t.title = :title, t.description = :description, t.link = :link, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, @Param("description") String description, 
			@Param("link") String link, @Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	@Modifying
	@Query("update VideoYouTubeTask t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);

}
