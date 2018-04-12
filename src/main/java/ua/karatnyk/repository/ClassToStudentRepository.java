package ua.karatnyk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;
@Repository
public interface ClassToStudentRepository extends JpaRepository<ClassToStudent, Integer>, JpaSpecificationExecutor<ClassToStudent>{
	
	@Query("Select c from ClassToStudent c where c.isDeleted = false")
	Page<ClassToStudent> findAllClassToStudentActived(Pageable pageable);
	
	@Query("Select c from ClassToStudent c where c.isDeleted = false")
	List<ClassToStudent> findAllClassToStudentsActived();
	
//	@Query("Select c from ClassToStudent c where c.isDeleted = false and c.title like %:filter%")
//	Page<ClassToStudent> findAllClassToStudentActivedWithTitleFilter(Pageable pageable, @Param("filter") String filter);
	
	@Query("Select c from ClassToStudent c where c.isDeleted = false and c.userClassTeacher = :userClassTeacher")
	ClassToStudent findClass(@Param("userClassTeacher") UserEntity userClassTeacher);
	
	@Modifying
	@Query("update ClassToStudent u set u.userClassTeacher = :userClassTeacher, u.lastUpdatedAt = :lastUpdatedAt, "
			+ "u.lastUpdateByUser = :lastUpdateByUser where u.id = :id")
	void updateUserClassTeacher(@Param("userClassTeacher") UserEntity userClassTeacher, @Param("lastUpdatedAt") Date lastUpdatedAt, 
			@Param("lastUpdateByUser") UserEntity lastUpdateByUser, @Param("id") int id);
	
	@Query("Select c from ClassToStudent c where c.isDeleted = false and c.letterToGrade = :letter and c.grade = :grade")
	ClassToStudent findActiveClass(@Param("letter") LetterToGrade letter, @Param("grade") Grade grade);
	
	@Modifying
	@Query("update ClassToStudent t set t.isDeleted = true, t.lastUpdateByUser = :lastUpdateByUser, "
			+ "t.lastUpdatedAt = :lastUpdatedAt where t.id = :id")
	void update(@Param("lastUpdateByUser") UserEntity currentUser, @Param("lastUpdatedAt") Date lastUpdatedAt, @Param("id") int id);

}
