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

import ua.karatnyk.domain.TheoreticalTaskRequest;
import ua.karatnyk.domain.TheoreticalTasksFilter;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.TheoreticalTaskMapper;
import ua.karatnyk.service.TheoreticalTaskService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.FileManager;


@Controller
@RequestMapping("/teacher")
@SessionAttributes({"taskModel"})
public class TheoreticalTaskController {
	
	@Autowired
	private TheoreticalTaskService taskService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/theoretical-tasks/{pageNumber}")
	public String showAllTheareticalTaskPage(Model model, @PathVariable("pageNumber") int pageNumber, Principal principal) {
		model.addAttribute("filterModel", new TheoreticalTasksFilter());
		try {
			Page<TheoreticalTask> page = taskService.getPagesTheoreticalTasksByUser(pageNumber, 20, "DESC", "createdAt", getCurrentUser(principal));
			int currentPage = page.getNumber()+1;
			int begin = Math.max(1, currentPage-1);
			int end = Math.min(begin+10, page.getNumber());
			
			model.addAttribute("pageModel", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", currentPage);
			model.addAttribute("taskList", TheoreticalTaskMapper.taskListToRequestList(page.getContent()));
			model.addAttribute("flag", true);
			return "teacher/tasks/theoreticals/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/theoretical-tasks/search/{pageNumber}")
	public String showAllTheareticalTaskPageWithSearch(Model model, @PathVariable("pageNumber") int pageNumber, Principal principal,
			@ModelAttribute("filterModel") TheoreticalTasksFilter filter) {
		
		try {
			filter.setUserEntity(getCurrentUser(principal));
			List<TheoreticalTask> list = taskService.getTheoreticalTasksWithFilter(filter);
			model.addAttribute("taskList", TheoreticalTaskMapper.taskListToRequestList(list));
			model.addAttribute("flag", false);
			return "teacher/tasks/theoreticals/view_all";
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/add-theoretical-task")
	public String addTaskPage(Model model) {
		
		model.addAttribute("requestModel", new TheoreticalTaskRequest());
		return "teacher/tasks/theoreticals/add";
	}
	
	@PostMapping("/add-theoretical-task")
	public String addNewsPage(@ModelAttribute("requestModel") @Valid TheoreticalTaskRequest request, 
			BindingResult result, Principal principal) {
		
		if(result.hasErrors()) {
			return "teacher/tasks/theoreticals/add";
		}
		
		try {
			TheoreticalTask task = TheoreticalTaskMapper.taskRequestToTaskForSave(request, getCurrentUser(principal));
			taskService.save(task);
			return "redirect:/teacher/theoretical-tasks/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("/theoretical-task-profile{taskId}")
	public String profileTaskShowPage(@PathVariable("taskId") int taskId, Model model, Principal principal) {
		try {
			if(taskService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			TheoreticalTask task = taskService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal));
			model.addAttribute("taskModel", TheoreticalTaskMapper.taskToRequestViewProfile(task));
			model.addAttribute("idUser", getCurrentUser(principal).getId());
			return "teacher/tasks/theoreticals/view_profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@GetMapping("/edit-theoretical-task{taskId}")
	public String profileTaskEditShowPage(@PathVariable("taskId") int taskId, Model model, Principal principal) {
		
		try {
			if(taskService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			TheoreticalTask task = taskService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal));
			model.addAttribute("taskModel", TheoreticalTaskMapper.taskToRequestViewProfile(task));
			return "teacher/tasks/theoreticals/edit";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	@PostMapping("/edit-theoretical-task{taskId}")
	public String profileTaskEditPage(@PathVariable("taskId") int taskId, @ModelAttribute("taskModel") @Valid TheoreticalTaskRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "teacher/tasks/theoreticals/edit";
		}
		if(request.getIdTask() != taskId) {
			return "home";
		}
		try {
			if(request.getFile().isEmpty()) {
				taskService.update(request.getTitle(), request.getDescription(), getCurrentUser(principal), new Date(), request.getIdTask());
			} else {
				taskService.update(request.getTitle(), request.getDescription(), FileManager.nameFile(request.getFile()), getCurrentUser(principal),
						new Date(), request.getIdTask());
				FileManager.saveFileInProject(request.getFile(), getCurrentUser(principal).getId(), Constants.FOLDER_FOR_USERS_FILES);
			}
			return "redirect:/teacher/theoretical-task-profile"+taskId;
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("remove-theoretical-task-profile{taskId}")
	public String deleteTask(Principal principal, @PathVariable("taskId") int taskId) {
		try {
			if(taskService.findTaskByIdNotDeleted(taskId, getCurrentUser(principal)) == null)
				return "home";
			taskService.update(getCurrentUser(principal), new Date(), taskId);
			return "redirect:/teacher/theoretical-tasks/1";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}


}
