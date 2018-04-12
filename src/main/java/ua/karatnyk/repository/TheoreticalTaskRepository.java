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
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;

@Repository
public interface TheoreticalTaskRepository extends JpaRepository<TheoreticalTask, Integer>, JpaSpecificationExecutor<TheoreticalTask>{
	
	@Query("Select c from TheoreticalTask c where c.isDeleted = false and c.createdByUser = :user")
	Page<TheoreticalTask> findAllNotDeletedByUser(Pageable pageable, @Param("user") UserEntity userEntity);
	
	@Query("Select c from TheoreticalTask c where c.isDeleted = false and c.createdByUser = :user")
	List<TheoreticalTask> findAllNotDeletedByUser(@Param("user") UserEntity userEntity);
	
	
	@Modifying
	@Query("update TheoreticalTask t set t.title = :title, t.description = :description, t.fileName = :fileName, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, @Param("description") String description, 
			@Param("fileName") String fileName, @Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	@Modifying
	@Query("update TheoreticalTask t set t.title = :title, t.description = :description, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, @Param("description") String description, 
			@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	
	@Modifying
	@Query("update TheoreticalTask t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);

}
