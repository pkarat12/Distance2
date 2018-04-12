package ua.karatnyk.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.VideoTaskRequest;
import ua.karatnyk.domain.VideoYouTubeTaskFilter;
import ua.karatnyk.entity.VideoYouTubeTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.VideoYouTubeTaskMapper;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.VideoYouTubeService;

@Controller
@RequestMapping("/teacher")
@SessionAttributes({"taskModel"})
public class VideoYouTubeTaskController {
	
	@Autowired
	private VideoYouTubeService videoYouTubeService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/video-youtube-tasks/{pageNumber}")
	public String showAllVideoYoutubeTaskPage(Model model, @PathVariable("pageNumber") int pageNumber, Principal principal) {
		model.addAttribute("filterModel", new VideoYouTubeTaskFilter());
		try {
			Page<VideoYouTubeTask> page = videoYouTubeService.getPagesVideoYouTubeTasksByUser(pageNumber, 20, "DESC", "createdAt", getCurrentUser(principal));
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("taskList", VideoYouTubeTaskMapper.taskListToRequestList(page.getContent()));
			model.addAttribute("flag", true);
			return "teacher/tasks/youtube_video/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/video-youtube-tasks/search/{pageNumber}")
	public String showAllTheareticalTaskPageWithSearch(Model model, @PathVariable("pageNumber") int pageNumber, Principal principal,
			@ModelAttribute("filterModel") VideoYouTubeTaskFilter filter) {
		
		try {
			filter.setUserEntity(getCurrentUser(principal));
			List<VideoYouTubeTask> list = videoYouTubeService.getVideoYouTubeTasksWithFilter(filter);
			model.addAttribute("taskList", VideoYouTubeTaskMapper.taskListToRequestList(list));
			model.addAttribute("flag", false);
			return "teacher/tasks/youtube_video/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/add-video-youtube-task")
	public String addTaskPage(Model model) {
		
		model.addAttribute("requestModel", new VideoTaskRequest());
		return "teacher/tasks/youtube_video/add";
	}
	
	@PostMapping("/add-video-youtube-task")
	public String addNewsPage(@ModelAttribute("requestModel") @Valid VideoTaskRequest request, 
			BindingResult result, Principal principal) {
		
		if(result.hasErrors()) {
			return "teacher/tasks/youtube_video/add";
		}
		
		try {
			VideoYouTubeTask task = VideoYouTubeTaskMapper.taskRequestToTaskForSave(request, getCurrentUser(principal));
			videoYouTubeService.save(task);
			return "redirect:/teacher/video-youtube-tasks/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("/video-youtube-task-profile{taskId}")
	public String profileTaskShowPage(@PathVariable("taskId") int taskId, Model model, Principal principal) {
		try {
			if(videoYouTubeService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			VideoYouTubeTask task = videoYouTubeService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal));
			model.addAttribute("taskModel", VideoYouTubeTaskMapper.taskToRequestViewProfile(task));
			return "teacher/tasks/youtube_video/view_profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/edit-video-youtube-task{taskId}")
	public String profileTaskEditShowPage(@PathVariable("taskId") int taskId, Model model, Principal principal) {
		
		try {
			if(videoYouTubeService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			VideoYouTubeTask task = videoYouTubeService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal));
			model.addAttribute("taskModel", VideoYouTubeTaskMapper.taskToRequestEditProfile(task));
			return "teacher/tasks/youtube_video/edit";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("/edit-video-youtube-task{taskId}")
	public String profileTaskEditPage(@PathVariable("taskId") int taskId, @ModelAttribute("taskModel") @Valid VideoTaskRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/tasks/youtube_video/edit";
		}
		if(request.getIdTask() != taskId) {
			return "home";
		}
		try {
			videoYouTubeService.update(request.getTitle(), request.getDescription(), request.getLink(), getCurrentUser(principal), new Date(), request.getIdTask());
			return "redirect:/teacher/video-youtube-task-profile"+taskId;
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("remove-video-youtube-task-profile{taskId}")
	public String deleteTask(Principal principal, @PathVariable("taskId") int taskId) {
		try {
			if(videoYouTubeService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			videoYouTubeService.update(getCurrentUser(principal), new Date(), taskId);
			return "redirect:/teacher/video-youtube-tasks/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}

}
