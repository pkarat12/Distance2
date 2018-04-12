package ua.karatnyk.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.GroupEditRequest;
import ua.karatnyk.domain.GroupFilter;
import ua.karatnyk.domain.GroupRequest;
import ua.karatnyk.domain.StudentFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.GroupMapper;
import ua.karatnyk.service.ClassToStudentService;
import ua.karatnyk.service.CourseService;
import ua.karatnyk.service.GroupService;
import ua.karatnyk.service.StudentService;
import ua.karatnyk.service.UserService;

@Controller
@SessionAttributes({ "courseModel", "classesListModel", "groupAddRequest", "coursesList"})
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassToStudentService classService;
	
	@GetMapping("/teacher/groups/{numberPage}")
	public String showAllGroupsPage(@PathVariable("numberPage") int numberPage, Model model, Principal principal) {
		
		model.addAttribute("filterModel", new GroupFilter());
		try {
			Page<Group> page = groupService.getPagesGroupsByUser(numberPage, 10, "DESC", "createdAt", getCurrentUser(principal));
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("groupList", GroupMapper.entitiesToRequestsForView(page.getContent()));
			model.addAttribute("flag", true);
			return "teacher/groups/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/groups/search/{numberPage}")
	public String showAllCoursesPageSearch(@PathVariable("numberPage") int numberPage, Model model, Principal principal, 
			@ModelAttribute("filterModel") GroupFilter filter) {
		try {
			filter.setUser(getCurrentUser(principal));
			List<Group> list =groupService.getGroupsWithFilter(filter);
			model.addAttribute("groupList", GroupMapper.entitiesToRequestsForView(list));
			model.addAttribute("flag", false);
			return "teacher/groups/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}	
	}
	
	@GetMapping("/teacher/group/add")
	public String showAddPage(Model model, Principal principal) {
		model.addAttribute("addGroupRequest", new GroupRequest());
		try {
			model.addAttribute("courseModel", courseService.findAllNotDeletedByUser(getCurrentUser(principal)));
			return "teacher/groups/add";
		} catch (NullPointerException e) {
			return "home";
		}
	}
	
	@PostMapping("/teacher/group/add")
	public String addPage(@ModelAttribute("addGroupRequest") @Valid GroupRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/groups/add";
		}
		try {
			Group group = GroupMapper.requestToEntityForAdd(request, getCurrentUser(principal));
			groupService.save(group);
			return "redirect:/teacher/groups/1";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/group/profile{idGroup}/{numberPage}")
	public String profileShowPage(@PathVariable("numberPage") int numberPage, @PathVariable("idGroup") int idGroup, Principal principal, Model model) {
		
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			GroupRequest request = GroupMapper.entityToRequestForView(group);
			model.addAttribute("groupModel", request);
			Page<UserEntity> page = new PageImpl<>(group.getUsers());
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());	
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("groupList", page.getContent());
			return "teacher/groups/view_profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/group/edit/profile{idGroup}/add-students/{numberPage}")
	public String addStudentShowPage(@PathVariable("idGroup") int idGroup, @PathVariable("numberPage") int numberPage, Principal principal, Model model) {
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			GroupRequest request = GroupMapper.entityToRequestForView(group);
			model.addAttribute("groupAddRequest", request);
			model.addAttribute("filterModel", new StudentFilter());
			model.addAttribute("classesListModel", classService.findAllActiveClasses());
			Page<UserEntity> page = studentService.getPagesStudentsWithoutGroup(numberPage, 10, "DESC", "createdAt", group);
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());	
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("studentsList", page.getContent());
			model.addAttribute("flag", true);
			return "teacher/groups/add_students";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("teacher/group/edit/profile{idGroup}/add-students/search/{numberPage}")
	public String addStudentSearchShowPage(@PathVariable("idGroup") int idGroup, @PathVariable("numberPage") int numberPage, 
			Principal principal, Model model, @ModelAttribute("filterModel") StudentFilter filter) {
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			GroupRequest request = GroupMapper.entityToRequestForView(group);
			model.addAttribute("groupAddRequest", request);
			List<UserEntity> list = studentService.getStudentsWithoutGroupWithFilter(group, filter);
			model.addAttribute("studentsList", list);
			model.addAttribute("flag", false);
			return "teacher/groups/add_students";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("/teacher/group/edit/profile{idGroup}/add-students/{numberPage}")
	public String addStudent(@ModelAttribute("groupAddRequest") GroupRequest request, @PathVariable("idGroup") int idGroup, 
			Principal principal, @PathVariable("numberPage") int numberPage) {
		try {
			Group group = GroupMapper.requestToEntityForAddStudents(request, getCurrentUser(principal));
			groupService.save(group);

			return "redirect:/teacher/group/profile"+idGroup+"/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/group/edit/profile{idGroup}/remove-students/{numberPage}")
	public String removeStudentShowPage(@PathVariable("idGroup") int idGroup, @PathVariable("numberPage") int numberPage, Principal principal, Model model) {
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			GroupRequest request = GroupMapper.entityToRequestForView(group);
			model.addAttribute("groupAddRequest", request);
			model.addAttribute("filterModel", new StudentFilter());
			model.addAttribute("classesListModel", classService.findAllActiveClasses());
			Page<UserEntity> page = studentService.getPagesStudentsWithGroup(numberPage, 10, "DESC", "createdAt", group);
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());	
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("studentsList", page.getContent());
			model.addAttribute("flag", true);
			return "teacher/groups/remove_students";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("teacher/group/edit/profile{idGroup}/remove-students/search/{numberPage}")
	public String removeStudentSearchShowPage(@PathVariable("idGroup") int idGroup, @PathVariable("numberPage") int numberPage, 
			Principal principal, Model model, @ModelAttribute("filterModel") StudentFilter filter) {
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			GroupRequest request = GroupMapper.entityToRequestForView(group);
			model.addAttribute("groupAddRequest", request);
			List<UserEntity> list = studentService.getStudentsWithGroupWithFilter(group, filter);
			model.addAttribute("studentsList", list);
			model.addAttribute("flag", false);
			return "teacher/groups/remove_students";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("/teacher/group/edit/profile{idGroup}/remove-students/{numberPage}")
	public String removeStudent(@ModelAttribute("groupAddRequest") GroupRequest request, @PathVariable("idGroup") int idGroup, 
			Principal principal, @PathVariable("numberPage") int numberPage) {
		try {
			Group group = GroupMapper.requestToEntityForRemoveStudents(request, getCurrentUser(principal));
			groupService.save(group);

			return "redirect:/teacher/group/profile"+idGroup+"/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("teacher/group/edit/profile{idGroup}")
	private String editGroupShowPage(@PathVariable("idGroup") int idGroup, Principal principal, Model model) {
		try {
			Group group = groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal));
			model.addAttribute("groupModelEdit", GroupMapper.entityToRequestForEdit(group));
			List<Course> listCourses = courseService.findAllNotDeletedByUser(getCurrentUser(principal));
			listCourses.remove(group.getCourse());
			model.addAttribute("coursesList", listCourses);
			return "teacher/groups/edit";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("teacher/group/edit/profile{idGroup}")
	public String editGroup(@PathVariable("idGroup") int idGroup, @ModelAttribute("groupModelEdit") @Valid GroupEditRequest request, BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/groups/edit";
		}
		try {
			groupService.update(request.getTitle(), request.getCourse(), getCurrentUser(principal), new Date(), request.getIdGroup());
			return "redirect:/teacher/group/profile"+idGroup+"/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("teacher/group/remove/profile{idGroup}")
	public String deleteGroup(@PathVariable("idGroup") int idGroup, Principal principal) {
		
		try {
			if(groupService.findGroupByIdNotDeletedByUser(idGroup, getCurrentUser(principal)) == null)
				return "home";
			groupService.delete(getCurrentUser(principal), new Date(), idGroup);
			return "redirect:/teacher/groups/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}

	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}
}
