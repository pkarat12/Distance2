package ua.karatnyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@GetMapping("")
	public String showMainTeacherPage() {
		return "teacher/main";
	}

}
