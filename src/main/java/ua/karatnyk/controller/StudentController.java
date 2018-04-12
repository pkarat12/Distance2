package ua.karatnyk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.LessonsForSudentsRequest;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Group;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.mapper.StudentCoursesMapper;
import ua.karatnyk.mapper.TheoreticalTaskMapper;
import ua.karatnyk.mapper.VideoYouTubeTaskMapper;
import ua.karatnyk.service.CourseService;
import ua.karatnyk.service.GroupService;
import ua.karatnyk.service.LessonService;
import ua.karatnyk.service.TheoreticalTaskService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.VideoYouTubeService;

@Controller
@RequestMapping("/student")
@SessionAttributes({"myCoursesModel"})
public class StudentController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private TheoreticalTaskService theoreticalTaskService;
	
	@Autowired
	private VideoYouTubeService videoYouTubeService;
	
	@GetMapping("")
	public String showMainStudentPage(Principal principal, Model model) {
		try {
			List<Group> groups = groupService.findWhithUserNotDeleted(getCurrentUser(principal));
			model.addAttribute("myCoursesModel", StudentCoursesMapper.coursesForLeftSide(groups));
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		return "student/main";
	}
	
	@GetMapping("/course{courseId}/{pageNumber}")
	public String showAllLessons(@PathVariable("pageNumber") int pageNumber, @PathVariable("courseId") int courseId, Model model, Principal principal) {
		try {
			List<Group> groups = groupService.findWhithUserNotDeleted(getCurrentUser(principal));
			model.addAttribute("myCoursesModel", StudentCoursesMapper.coursesForLeftSide(groups));
			if(groups.stream().filter(p -> p.getId() == courseId).findAny() == null)
				return "home";
			Course course = courseService.findCourseByIdNotDeleted(courseId);
			if(course == null) 
				return "home";
			Page<Lesson> page = lessonService.find(pageNumber, 25, "DESC", "createdAt", course);
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			List<LessonsForSudentsRequest> lessons = StudentCoursesMapper.lessonsForStudentsRequest(page.getContent());
			model.addAttribute("page", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("lessonsModel", lessons);
			model.addAttribute("idCourse", courseId);
			model.addAttribute("flag", true);
			return "student/view_all_lessons";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}	
	}
	
	@GetMapping("/course{courseId}/search")
	public String showAllClassWithSearch(@PathVariable("courseId") int courseId, Principal principal, @RequestParam("search") String search, Model model) {
		try {
			List<Group> groups = groupService.findWhithUserNotDeleted(getCurrentUser(principal));
			model.addAttribute("myCoursesModel", StudentCoursesMapper.coursesForLeftSide(groups));
			if(groups.stream().filter(p -> p.getId() == courseId).findAny() == null)
				return "home";
			Course course = courseService.findCourseByIdNotDeleted(courseId);
			if(course == null) 
				return "home";
			List<Lesson> lessons = lessonService.find(course, search);
			model.addAttribute("lessonsModel", StudentCoursesMapper.lessonsForStudentsRequest(lessons));
			model.addAttribute("idCourse", courseId);
			model.addAttribute("flag", false);
			return "student/view_all_lessons";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}	
	}
	
	@GetMapping("/course{courseId}/lesson{lessonId}/profile-theoretical-task{taskId}")
	public String showTheoreticalTask(@PathVariable("courseId") int courseId, @PathVariable("taskId") int taskId, @PathVariable("lessonId") int lessonId,
			Model model, Principal principal) {
		try {
			List<Group> groups = groupService.findWhithUserNotDeleted(getCurrentUser(principal));
			model.addAttribute("myCoursesModel", StudentCoursesMapper.coursesForLeftSide(groups));
			if(groups.stream().filter(p -> p.getId() == courseId).findAny() == null)
				return "home";
			Course course = courseService.findCourseByIdNotDeleted(courseId);
			if(course == null) 
				return "home";
			Lesson lesson = lessonService.find(course, lessonId);
			TheoreticalTask task = theoreticalTaskService.find(lesson, taskId);
			if(task == null) 
				return "home";
			model.addAttribute("taskModel", TheoreticalTaskMapper.taskToRequestViewProfile(task));
			model.addAttribute("idCourse", courseId);
			model.addAttribute("subjectModel", course.getCreatedByUser().getSubject().title.toUpperCase());
			return "student/theoretical_task_profile";
		} catch (NullPointerException e) {
			return "home";
		}
		
	}
	
	@GetMapping("/course{courseId}/lesson{lessonId}/profile-video-youtube-task{taskId}")
	public String showVideotask(@PathVariable("courseId") int courseId, @PathVariable("taskId") int taskId, @PathVariable("lessonId") int lessonId,
			Model model, Principal principal) {
		try {
			List<Group> groups = groupService.findWhithUserNotDeleted(getCurrentUser(principal));
			model.addAttribute("myCoursesModel", StudentCoursesMapper.coursesForLeftSide(groups));
			if(groups.stream().filter(p -> p.getId() == courseId).findAny() == null)
				return "home";
			Course course = courseService.findCourseByIdNotDeleted(courseId);
			if(course == null) 
				return "home";
			Lesson lesson = lessonService.find(course, lessonId);
			VideoYouTubeTask task = videoYouTubeService.find(lesson, taskId);
			if(task == null) 
				return "home";
			model.addAttribute("taskModel", VideoYouTubeTaskMapper.taskToRequestViewProfile(task));
			model.addAttribute("idCourse", courseId);
			model.addAttribute("subjectModel", course.getCreatedByUser().getSubject().title.toUpperCase());
			return "student/video_task_profile";
		} catch (NullPointerException e) {
			return "home";
		}
	}
	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}
	

}
