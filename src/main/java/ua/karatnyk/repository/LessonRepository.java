package ua.karatnyk.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.UserEntity;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>, JpaSpecificationExecutor<Lesson>{
	
	@Modifying
	@Query("update Lesson t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	
	@Modifying
	@Query("update Lesson t set t.visibility = :visibility, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("visibility") Boolean visibility, @Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);
	
	@Modifying
	@Query("update Lesson t set t.title = :title, t.description = :description, t.number = :number, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("title") String title, @Param("description") String description, @Param("number") String number,
			@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);

}
