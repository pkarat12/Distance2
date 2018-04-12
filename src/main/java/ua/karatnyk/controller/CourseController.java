package ua.karatnyk.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.karatnyk.domain.CourseFilter;
import ua.karatnyk.domain.CourseRequest;
import ua.karatnyk.domain.LessonFilter;
import ua.karatnyk.domain.LessonRequest;
import ua.karatnyk.domain.TaskFilter;
import ua.karatnyk.entity.Course;
import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.mapper.CourseMapper;
import ua.karatnyk.mapper.LessonMapper;
import ua.karatnyk.service.CourseService;
import ua.karatnyk.service.LessonService;
import ua.karatnyk.service.TheoreticalTaskService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.VideoYouTubeService;
import ua.karatnyk.service.utilities.Utilities;

@Controller
@SessionAttributes({ "gradeModel", "gradeEditModel", "addLessonRequest", "lessonAddTasksRequest"})
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private TheoreticalTaskService theoreticalTaskService;
	
	@Autowired
	private VideoYouTubeService videoYouTubeService;
	
	@GetMapping("/teacher/courses/{numberPage}")
	public String showAllCoursesPage(@PathVariable("numberPage") int numberPage, Model model, Principal principal) {
		model.addAttribute("filterModel", new CourseFilter());
		model.addAttribute("gradeModel", Grade.values());
		try {
			Page<Course> page = courseService.getPagesCoursesByUser(numberPage, 20, "DESC", "createdAt", getCurrentUser(principal));
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("courseList", CourseMapper.entitiesToRequestsForView(page.getContent()));
			model.addAttribute("flag", true);
			return "teacher/courses/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/courses/search/{numberPage}")
	public String showAllCoursesPageSearch(@PathVariable("numberPage") int numberPage, Model model, Principal principal, 
			@ModelAttribute("filterModel") CourseFilter filter) {
		try {
			filter.setUserEntity(getCurrentUser(principal));
			List<Course> list =courseService.getCoursesWithFilter(filter);
			model.addAttribute("courseList", CourseMapper.entitiesToRequestsForView(list));
			model.addAttribute("flag", false);
			return "teacher/courses/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}	
	}
	
	@GetMapping("/teacher/course/add")
	public String showAddPage(Model model) {
		model.addAttribute("requestModel", new CourseRequest());
		model.addAttribute("gradeModel", Grade.values());
		return "teacher/courses/add";
	}
	
	@PostMapping("/teacher/course/add")
	public String addPage(@ModelAttribute("requestModel") @Valid CourseRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/courses/add";
		}
		try {
			Course course = CourseMapper.requestToEntityForAdd(request, getCurrentUser(principal));
			courseService.save(course);
			return "redirect:/teacher/courses/1";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/course/profile{courseId}/{numberPage}")
	public String showProfilePage(@PathVariable("courseId") int courseId, @PathVariable("numberPage") int numberPage, Model model, Principal principal) {
		model.addAttribute("filterModel", new LessonFilter());
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			model.addAttribute("requestModel", CourseMapper.entityToRequestForView(course));
			
			Page<Lesson> page = lessonService.findAllPagesNotDeletedByUserByCourse(numberPage, 10, "DESC", "createdAt", getCurrentUser(principal), course);
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("lessonsList", LessonMapper.lessonsToRequestsForView(page.getContent()));
			model.addAttribute("flag", true);
			return "teacher/courses/view_profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "redirect:/teacher/courses/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/course/profile{courseId}/search/{numberPage}")
	public String showProfileSearchPage(@PathVariable("courseId") int courseId, @PathVariable("numberPage") int numberPage, @ModelAttribute("filterModel") LessonFilter filter,
			Model model, Principal principal) {
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			model.addAttribute("requestModel", CourseMapper.entityToRequestForView(course));
			List<Lesson> list = lessonService.findAllNotDeletedByUserByCourseWithFilter(getCurrentUser(principal), course, filter);
			model.addAttribute("lessonsList", LessonMapper.lessonsToRequestsForView(list));
			model.addAttribute("flag", false);
			return "teacher/courses/view_profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "redirect:/teacher/courses/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/course/edit/profile{courseId}")
	public String showEditPage(@PathVariable("courseId") int courseId, Model model, Principal principal) {
		
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			model.addAttribute("requestModelEdit", CourseMapper.entityToRequestForView(course));
			List<Grade> grades = new ArrayList<>(Arrays.asList(Grade.values()));
			grades.remove(course.getGrade());
			model.addAttribute("gradeEditModel", grades);
			return "teacher/courses/edit";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "redirect:/teacher/courses/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("/teacher/course/edit/profile{courseId}")
	public String editPage(@PathVariable("courseId") int courseId, @ModelAttribute("requestModelEdit") @Valid CourseRequest request, BindingResult result, Principal principal) {
		
		if(result.hasErrors()) {
			return "teacher/courses/edit";
		}
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			courseService.update(request.getTitle(), request.getDescription(), request.getGrade(), getCurrentUser(principal), new Date(), course.getId());
			return "redirect:/teacher/course/profile"+courseId+"/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "redirect:/teacher/courses/1"; 
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/course/remove/profile{courseId}")
	public String removePage(@PathVariable("courseId") int courseId, Principal principal) {
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			courseService.delete(course.getLastUpdateByUser(), new Date(), course.getId());
			return "redirect:/teacher/courses/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "redirect:/teacher/courses/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	@GetMapping("/teacher/course/profile{courseId}/lesson/add")
	public String addLessonPage(@PathVariable("courseId") int courseId, Model model, Principal principal) {
		LessonRequest request = new LessonRequest();
		try {
			Course course = courseService.findCourseByIdNotDeletedByUser(courseId, getCurrentUser(principal));
			if(course == null)
				return "home";
			request.setCourse(course);
			List<Lesson> lessons = lessonService.findAllNotDeletedByUserByCourse(getCurrentUser(principal), course);
			request.setNumber(Utilities.nextNumberLesson(lessons));
			model.addAttribute("addLessonRequest", request);
			return "teacher/lessons/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
		
	}
	
	@PostMapping("/teacher/course/profile{courseId}/lesson/add")
	public String addLesson(@PathVariable("courseId") int courseId, Model model,
			@ModelAttribute("addLessonRequest") @Valid LessonRequest request, BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/lessons/add";
		}
		try {
			Lesson lesson = LessonMapper.requestToLessonForAdd(request, getCurrentUser(principal));
			lessonService.save(lesson);
			return "redirect:/teacher/course/profile"+courseId+"/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	@GetMapping("/teacher/lesson/profile{lessonId}")
	public String showLessonProfilePage(@PathVariable("lessonId") int lessonId,
			Model model, Principal principal) {
		try {
			Lesson lesson = lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId);
			model.addAttribute("lessonProfileModel", LessonMapper.lessonToRequestForView(lesson));
			return "teacher/lessons/view_profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "home";
		}	
	}
	@GetMapping("/teacher/lesson/profile{lessonId}/add-tasks")
	public String addTasksToLessonsShowPage(@PathVariable("lessonId") int lessonId,
			Model model, Principal principal) {
		if(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId) == null)
			return "home";
		try {
			LessonRequest request = LessonMapper.lessonToRequestForView(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId));
			model.addAttribute("lessonAddTasksRequest", request);
			List<TheoreticalTask> theoreticalTasks = theoreticalTaskService.findAllNotDeletedByUser(getCurrentUser(principal));
			theoreticalTasks.removeAll(request.getTheoreticalTasks());
			model.addAttribute("theoreticalTasksModel", theoreticalTasks);
			List<VideoYouTubeTask> videoYouTubes = videoYouTubeService.findAllNotDeletedByUser(getCurrentUser(principal));
			videoYouTubes.removeAll(request.getVideoYouTubeTasks());
			model.addAttribute("videoTasksModel", videoYouTubes);
			model.addAttribute("filterModel", new TaskFilter());
			return "teacher/lessons/add_tasks";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/teacher/lesson/profile{lessonId}/add-tasks/search")
	public String addTasksToLessonsSearchShowPage(@PathVariable("lessonId") int lessonId,
			Model model, Principal principal, @ModelAttribute("filterModel") TaskFilter filter) {
		if(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId) == null)
			return "home";
		try {
			System.out.println(filter.getSearch());
			LessonRequest request = LessonMapper.lessonToRequestForView(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId));
			model.addAttribute("lessonAddTasksRequest", request);
			
			List<TheoreticalTask> theoreticalTasks = theoreticalTaskService.findAllNotDeletedByUser(getCurrentUser(principal));
			theoreticalTasks.removeAll(request.getTheoreticalTasks());
			model.addAttribute("theoreticalTasksModel", Utilities.applyFilterT(theoreticalTasks, filter.getSearch()));
			
			List<VideoYouTubeTask> videoYouTubes = videoYouTubeService.findAllNotDeletedByUser(getCurrentUser(principal));
			videoYouTubes.removeAll(request.getVideoYouTubeTasks());
			model.addAttribute("videoTasksModel", Utilities.applyFilterV(videoYouTubes, filter.getSearch()));
			
			return "teacher/lessons/add_tasks";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	
	@PostMapping("/teacher/lesson/profile{lessonId}/add-tasks")
	private String addTasksToLesson(@PathVariable("lessonId") int lessonId, @ModelAttribute("lessonAddTasksRequest") LessonRequest request, Principal principal) {
		
		try {
			Lesson lesson = LessonMapper.requestToLessonForAddTasks(request, getCurrentUser(principal));
			lessonService.save(lesson);
			
			return "redirect:/teacher/lesson/profile"+lessonId;
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}
	
	@GetMapping("/teacher/lesson/profile{lessonId}/remove-tasks")
	public String removeTasksToLessonsShowPage(@PathVariable("lessonId") int lessonId,
			Model model, Principal principal) {
		if(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId) == null)
			return "home";
		try {
			LessonRequest request = LessonMapper.lessonToRequestForView(lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), lessonId));
			model.addAttribute("lessonAddTasksRequest", request);
			List<TheoreticalTask> theoreticalTasks = request.getTheoreticalTasks();
			model.addAttribute("theoreticalTasksModel", theoreticalTasks);
			List<VideoYouTubeTask> videoYouTubeTasks = request.getVideoYouTubeTasks();
			model.addAttribute("videoTasksModel", videoYouTubeTasks);
			return "teacher/lessons/remove_tasks";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	@PostMapping("/teacher/lesson/profile{lessonId}/remove-tasks")
	public String removeTasksToLesson(@PathVariable("lessonId") int lessonId, @ModelAttribute("lessonAddTasksRequest") LessonRequest request, Principal principal) {
		
		try {
			
			Lesson lesson = LessonMapper.requestToLessonForRemoveTasks(request, getCurrentUser(principal));
			lessonService.save(lesson);
			
			return "redirect:/teacher/lesson/profile"+lessonId;
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("/teacher/lesson/profile{idLesson}/active")
	public String changeStatusLesson(@PathVariable("idLesson") int idLesson, Principal principal) {
		try {
			Lesson lesson = lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), idLesson);
			if(lesson == null) {
				return "home";
			}
			if(lesson.isVisibility() == true) {
				lessonService.changeStatus(new Boolean("false"), getCurrentUser(principal), new Date(), lesson.getId());
				return "redirect:/teacher/lesson/profile"+idLesson;
			} else {
				lessonService.changeStatus(new Boolean("true"), getCurrentUser(principal), new Date(), lesson.getId());
				return "redirect:/teacher/lesson/profile"+idLesson;
			}
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	@GetMapping("/teacher/lesson/remove/profile{idLesson}")
	public String removeLesson(@PathVariable("idLesson") int idLesson, Principal principal) {
		
		try {
			Lesson lesson = lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), idLesson);
			if(lesson == null) {
				return "home";
			}
			lessonService.delete(getCurrentUser(principal), new Date(), idLesson);
			return "redirect:/teacher/course/profile"+lesson.getCourse().getId()+"/1";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}	
	}
	
	@GetMapping("/teacher/lesson/edit/profile{idLesson}")
	public String editLessonPage(@PathVariable("idLesson") int idLesson, Principal principal, Model model) {
		
		try {
			Lesson lesson = lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), idLesson);
			if(lesson == null) {
				return "home";
			}
			model.addAttribute("lessonEditModel", LessonMapper.lessonToRequestForView(lesson));
			return "teacher/lessons/edit";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	@PostMapping("/teacher/lesson/edit/profile{idLesson}")
	public String editLesson(@PathVariable("idLesson") int idLesson, Principal principal, @ModelAttribute("lessonEditModel") @Valid LessonRequest request, BindingResult result) {
		
		if(result.hasErrors()) {
			return "teacher/lessons/edit";
		}
		
		try {
			Lesson lesson = lessonService.findByIdNotDeletedByUser(getCurrentUser(principal), idLesson);
			if(lesson == null) {
				return "home";
			}
			lessonService.update(request.getTitle(), request.getDescription(), request.getNumber(), getCurrentUser(principal), new Date(), idLesson);
			return "redirect:/teacher/lesson/profile"+idLesson;
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
}
