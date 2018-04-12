package ua.karatnyk.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
	
	@Modifying
	@Query("update Group t set t.title = :title, t.course = :course, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, 
			@Param("course") Course course, @Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	
	@Modifying
	@Query("update Group t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);

}
