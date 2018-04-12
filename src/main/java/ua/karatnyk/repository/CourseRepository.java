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

import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course>{
	
	@Query("Select c from Course c where c.isDeleted = false and c.createdByUser = :user")
	Page<Course> findAllNotDeletedByUser(Pageable pageable, @Param("user") UserEntity userEntity);
	
	@Query("Select c from Course c where c.isDeleted = false and c.createdByUser = :user")
	List<Course> findAllNotDeletedByUser(@Param("user") UserEntity userEntity);
	
	
	@Modifying
	@Query("update Course t set t.title = :title, t.description = :description, t.grade = :grade, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, @Param("description") String description, 
			@Param("grade") Grade grade, @Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	
	@Modifying
	@Query("update Course t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	

}
