package ua.karatnyk.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.karatnyk.domain.GroupEditRequest;
import ua.karatnyk.domain.GroupRequest;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;

public class GroupMapper {
	
	public static Group requestToEntityForAdd(GroupRequest request, UserEntity currentUser) {
		Group group = new Group();
		group.setCourse(request.getCourse());
		group.setCreatedByUser(currentUser);
		group.setLastUpdateByUser(currentUser);
		group.setTitle(request.getTitle());
		return group;
	}
	
	public static GroupRequest entityToRequestForView(Group group) {
		GroupRequest request = new GroupRequest();
		request.setCourse(group.getCourse());
		request.setIdGroup(group.getId());
		request.setTitle(group.getTitle());
		request.setStudents(group.getUsers());
		
		return request;
	}
	
	public static GroupEditRequest entityToRequestForEdit(Group group) {
		GroupEditRequest request = new GroupEditRequest();
		request.setCourse(group.getCourse());
		request.setIdGroup(group.getId());
		request.setTitle(group.getTitle());
		
		return request;
	}
	
	public static List<GroupRequest> entitiesToRequestsForView(List<Group> groups) {
		List<GroupRequest> groupRequests = new ArrayList<>();
		for(Group g: groups) {
			GroupRequest request = GroupMapper.entityToRequestForView(g);
			groupRequests.add(request);
		}
		return groupRequests;
	}
	
	public static Group requestToEntityForAddStudents(GroupRequest request, UserEntity currentUser) {
		Group group = new Group();
		group.setCourse(request.getCourse());
		group.setId(request.getIdGroup());
		group.setLastUpdateByUser(currentUser);
		group.setLastUpdatedAt(new Date());
		request.getStudents().addAll(request.getNewStudents());
		group.setUsers(request.getStudents());
		group.setTitle(request.getTitle());
		return group;
	}
	
	public static Group requestToEntityForRemoveStudents(GroupRequest request, UserEntity currentUser) {
		Group group = new Group();
		group.setCourse(request.getCourse());
		group.setId(request.getIdGroup());
		group.setLastUpdateByUser(currentUser);
		group.setLastUpdatedAt(new Date());
		request.getStudents().removeAll(request.getNewStudents());
		group.setUsers(request.getStudents());
		group.setTitle(request.getTitle());
		return group;
	}

}
