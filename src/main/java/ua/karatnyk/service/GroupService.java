package ua.karatnyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import ua.karatnyk.domain.GroupFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;

public interface GroupService {
	
	List<Group> findAllNotDeletedByUser(UserEntity entity);
	
	Page<Group> getPagesGroupsByUser(int pageNumber, int pageSize, String sort, String sortByField, UserEntity userEntity);
	
	Page<Group> getPagesGroupsWithFilter(int pageNumber, int pageSize, String sort, String sortByField, GroupFilter filter);
	List<Group> getGroupsWithFilter(GroupFilter filter);
	
	void save(Group group);
	
	Group findGroupByIdNotDeletedByUser(int id, UserEntity user);
	
	void update(String title, Course course, UserEntity currentUser, Date lastUpdatedAt, int id);
	
	void delete(UserEntity currentUser, Date lastUpdatedAt, int id); 
	
	List<Group> findWhithUserNotDeleted(UserEntity userEntity);

}
