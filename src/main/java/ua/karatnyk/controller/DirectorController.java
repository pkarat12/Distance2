package ua.karatnyk.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.ClassAddRequest;
import ua.karatnyk.domain.ClassEditRequest;
import ua.karatnyk.domain.ClassFilter;
import ua.karatnyk.domain.ClassViewRequest;
import ua.karatnyk.domain.EmailRequest;
import ua.karatnyk.domain.PasswordEditRequest;
import ua.karatnyk.domain.StudentAddRequest;
import ua.karatnyk.domain.StudentFilter;
import ua.karatnyk.domain.StudentViewRequest;
import ua.karatnyk.domain.TeacherAddRequest;
import ua.karatnyk.domain.TeacherFilter;
import ua.karatnyk.domain.TeachersViewRequest;
import ua.karatnyk.domain.UserEditRequest;
import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.mapper.ClassMapper;
import ua.karatnyk.mapper.StudentMapper;
import ua.karatnyk.mapper.TeacherMapper;
import ua.karatnyk.mapper.UserMapper;
import ua.karatnyk.service.ClassToStudentService;
import ua.karatnyk.service.MailService;
import ua.karatnyk.service.StudentService;
import ua.karatnyk.service.TeacherService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.Mail;
import ua.karatnyk.service.utilities.MailManager;


@Controller
@RequestMapping("/director")
@SessionAttributes({ "letterModel", "gradeModel",
	"classesListModel", "userEditModel", "classesListModelForEdit", "subjectEditModel", 
	"subjectModel", "listTeachersModel", "editPasswordModel", "emailUserModel", "classToStudentModel" })
public class DirectorController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClassToStudentService classService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MailService mailService;
	
	@GetMapping
	public String showMainDirectorPage() {
		return "director/main";
	}
	
	//----------------------Вчителі-----------------------
	@GetMapping("/teachers/{pageNumber}")
	public String showTeacherTablePage(Model model, @PathVariable("pageNumber") int pageNumber) {
		model.addAttribute("filterModel", new TeacherFilter());
		Page<UserEntity> page = teacherService.getPagesTeachers(pageNumber, 20, "DESC", "createdAt");
		int currentPage = page.getNumber()+1;
		int begin = Math.max(1, currentPage-1);
		int end = Math.min(begin+10, page.getNumber());
		
		model.addAttribute("teachersList", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", currentPage);
		model.addAttribute("teacherListModel", TeacherMapper.listTeacherEntitiesToListTeachersViewRequest(page.getContent()));
		model.addAttribute("subjectModel", Subject.values());
		model.addAttribute("flag", true);
		return "director/teachers/view_teachers";
	}
	
	@GetMapping("/search/teachers/{pageNumber}")
	public String showTeacherTablePageWithFilter(Model model, @PathVariable("pageNumber") int pageNumber, @ModelAttribute("filterModel") TeacherFilter filter) {
		
		List<UserEntity> list = teacherService.getPagesTeachersWithFilter(filter);
		model.addAttribute("teacherListModel", TeacherMapper.listTeacherEntitiesToListTeachersViewRequest(list));
		model.addAttribute("flag", false);
		return "director/teachers/view_teachers";
	}
	
	@GetMapping("/teachers/remove-filter") 
	public String removeFilter() {
		
		return "redirect:/director/teachers/1";
	}
	
	@GetMapping("/add_teacher")
	public String addTeacherShowPage(Model model) {
		model.addAttribute("teacherAddRequestModel", new TeacherAddRequest());
		model.addAttribute("subjectModel", Subject.values());
		
		return "director/teachers/add_teacher";
	}
	
	@PostMapping("/add_teacher")
	public String addTeacher(@ModelAttribute("teacherAddRequestModel") @Valid TeacherAddRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "director/teachers/add_teacher";
		}
		try {
			UserEntity entity = TeacherMapper.teacherAddRequestToUserEntity(request, userService.findByLoginActive(principal.getName()));
			userService.saveUser(entity);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		return "redirect:/director/teachers/1";
	}
	@GetMapping("/profile-teacher{idTeacher}")
	public String showProfileTeacher(@PathVariable("idTeacher") int idTeacher, Model model) {
		
		UserEntity teacher = userService.findByIdActive(idTeacher);
		if(classService.findClass(teacher) != null)
			model.addAttribute("classModel", ClassMapper.classEntityToViewRequest(classService.findClass(teacher)));
		TeachersViewRequest request = TeacherMapper.userEntityToTeachersViewRequest(teacher);
		model.addAttribute("teacherModel", request);
		
		return "director/teachers/view_profile_teacher";
	}
	
	//------------------Учні-------------------------------
	
	@GetMapping("/students/{pageNumber}")
	public String showStudentTablePage(Model model, @PathVariable("pageNumber") int pageNumber) {
		model.addAttribute("classesListModel", classService.findAllActiveClasses());
		model.addAttribute("filterModel", new StudentFilter());
		Page<UserEntity> page = studentService.getPagesStudents(pageNumber, 20, "DESC", "createdAt");
		int currentPage = page.getNumber()+1;
		int begin = Math.max(1, currentPage-1);
		int end = Math.min(begin+10, page.getNumber());
		
		model.addAttribute("studentsList", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", currentPage);
		model.addAttribute("studentListModel", StudentMapper.listUserEntityToListStudentRequests(page.getContent()));
		model.addAttribute("flag", true);
		return "director/students/view_students";
	}
	
	@PostMapping("/search/students/{pageNumber}")
	public String showStudentTablePageWithFilter(Model model, @PathVariable("pageNumber") int pageNumber, @ModelAttribute("filterModel") StudentFilter filter) {
		
		List<UserEntity> list = studentService.getStudentsWithFilter(filter);
		
		model.addAttribute("studentListModel", StudentMapper.listUserEntityToListStudentRequests(list));
		model.addAttribute("flag", false);
		return "director/students/view_students";
	}
	
	@GetMapping("/students/remove-filter") 
	public String removeFilterStudent() {
		
		return "redirect:/director/students/1";
	}
	
	@GetMapping("/add_student")
	public String addStudentShowPage(Model model) {
		model.addAttribute("studentAddRequestModel", new StudentAddRequest());
		model.addAttribute("classesListModel", classService.findAllActiveClasses());
		return "director/students/add_student";
	}
	
	@PostMapping("/add_student")
	public String addStudent(@ModelAttribute("studentAddRequestModel") @Valid StudentAddRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "director/students/add_student";
		}
		try {
			UserEntity entity = StudentMapper.studentAddRequestToUserEntity(request, this.findCurrentUser(principal));
			userService.saveUser(entity);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		return "redirect:/director/students/1";
	}
	@GetMapping("/profile-student{idStudent}")
	public String showProfileStudent(@PathVariable("idStudent") int idStudent, Model model) {
		
		UserEntity student = userService.findByIdActive(idStudent);
		if(student.getRole() != Role.ROLE_STUDENT) {
			return "redirect:/director/students/1";
		}
			
		StudentViewRequest request = StudentMapper.userEntityToStudentViewRequest(student);
		model.addAttribute("studentModel", request);
		
		return "director/students/view_profile_student";
	}

	//------------------класи-------------------------------
	
	@GetMapping("/classes/{pageNumber}")
	public String showClassesTablePage(Model model, @PathVariable("pageNumber") int pageNumber) {
		model.addAttribute("gradeModel", Grade.values());
		Page<ClassToStudent> page = classService.getPagesClasses(pageNumber, 20, "DESC", "createdAt");
		int currentPage = page.getNumber()+1;
		int begin = Math.max(1, currentPage-1);
		int end = Math.min(begin+10, page.getNumber());
		model.addAttribute("classesList", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", currentPage);
		model.addAttribute("classesListModel", ClassMapper.listClassEntityToListClassViewRequest(page.getContent()));
		model.addAttribute("flag", true);
		return "director/classes/view_classes";
	}
	
	@GetMapping("/search/classes/{pageNumber}")
	public String showClassesTablePageWithFilter(Model model, @PathVariable("pageNumber") int pageNumber, @RequestParam("gradeFilter") Grade filter) {
		model.addAttribute("gradeModel", Grade.values());
		List<ClassToStudent> list = classService.getClassesWithFilter(new ClassFilter(filter));
		model.addAttribute("classesListModel", ClassMapper.listClassEntityToListClassViewRequest(list));
		model.addAttribute("flag", false);
		return "director/classes/view_classes_search";
	}
	
	@GetMapping("/classes/remove-filter") 
	public String removeFilterClasses() {
		
		return "redirect:/director/classes/1";
	}
	
	
	@GetMapping("/add_class")
	public String addClassShowPage(Model model) {
		model.addAttribute("classAddRequestModel", new ClassAddRequest());
		model.addAttribute("letterModel", LetterToGrade.values());
		model.addAttribute("gradeModel", Grade.values());
		model.addAttribute("listTeachersModel", teacherService.findAllActiveTeachers());
		return "director/classes/add_class";
	}
	
	@PostMapping("/add_class")
	public String addClass(@ModelAttribute("classAddRequestModel") @Valid ClassAddRequest request, 
			BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "director/classes/add_class";
		}
		try {
			if(classService.findClass(request.getUserClassTeacher()) != null) {
				ClassToStudent classToStudent = classService.findClass(request.getUserClassTeacher());
				classService.updateUserClassTeacher(null, new Date(), this.findCurrentUser(principal), classToStudent.getId());
			}
			ClassToStudent classToStudent = ClassMapper.addRequestToClass(request, this.findCurrentUser(principal));
			classService.saveClassToStudent(classToStudent);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		return "redirect:/director/classes/1";
	}
	
	@GetMapping("/profile-class{idClass}")
	public String showProfileClass(@PathVariable("idClass") int idClass, Model model) {
		
		ClassViewRequest request = ClassMapper.classEntityToViewRequest(classService.findClass(idClass));
		model.addAttribute("classModel", request);
		
		return "director/classes/view_profile_class";
	}
	
	@GetMapping("/edit/class-teacher{idClass}")
	public String showEditClassTeacherPage(Model model, @PathVariable("idClass") int idClass) {
		ClassEditRequest request = ClassMapper.classToStudentToClassEditRequest(classService.findClass(idClass));
		List<UserEntity> list = new ArrayList<>();
		if(request.getUserClassTeacher() == null) {
			model.addAttribute("flag", false);
			list.addAll(teacherService.findAllActiveTeachers());
			model.addAttribute("listTeachersModel", list);
		} else {
			model.addAttribute("flag", true);
			list.addAll(teacherService.findAllActiveTeachers());
			list.remove(request.getUserClassTeacher());
			model.addAttribute("listTeachersModel", list);
		}
		model.addAttribute("classToStudentModel", request);
		return "director/classes/edit_class";
	}
	
	@PostMapping("/edit/class-teacher{idClass}")
	public String editClassTeacherPage(@ModelAttribute("classToStudentModel") ClassEditRequest request, 
			@PathVariable("idClass") int idClass, Principal principal) {
		try {
			if(classService.findClass(request.getUserClassTeacher()) != null) {
				ClassToStudent classToStudent = classService.findClass(request.getUserClassTeacher());
				classService.updateUserClassTeacher(null, new Date(), this.findCurrentUser(principal), classToStudent.getId());
			}
			ClassToStudent classToStudent = ClassMapper.classEditRequestToClassToStudent(request);
			classService.updateUserClassTeacher(classToStudent.getUserClassTeacher(), new Date(), this.findCurrentUser(principal), classToStudent.getId());
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		return "redirect:/director/profile-class"+idClass;
	}
	
	@GetMapping("/remove/profile-class{idClass}")
	public String removeClass(@PathVariable("idClass") int idClass, Principal principal) {
		
		try {
			ClassToStudent classToStudent = classService.findClass(idClass);
			if(classToStudent == null)
				return "home";
			classService.delete(findCurrentUser(principal), new Date(), idClass);
		} catch (NullPointerException e) {
			return "home";
		}
		return "redirect:/director/classes/1";
	}
	
	//----------------------------Спільне для учнів і вчителів---------------------------
	private UserEntity findCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}
	
	@GetMapping("/edit/password-user{userId}")
	public String showPasswordPage(@PathVariable("userId") int userId, Model model) {
		try {
			UserEntity userEntity = userService.findByIdActive(userId);
			if(userEntity == null || userEntity.getRole() != Role.ROLE_STUDENT && userEntity.getRole() != Role.ROLE_TEACHER) {
				return "home";
			}
			String role = userEntity.getRole()==Role.ROLE_STUDENT?"УЧНІ":"ВЧИТЕЛІ";
			model.addAttribute("role", role);
			model.addAttribute("editPasswordModel", new PasswordEditRequest(userId));
			return "director/users/edit_password_user";
		} catch (NullPointerException e) {
			e.printStackTrace();	
			return "home";
		}
	}
	@PostMapping("/edit/password-user{userId}")
	public String editUserPassword(@ModelAttribute("editPasswordModel") @Valid PasswordEditRequest request, 
			BindingResult result, Principal principal, @PathVariable("userId") int userId) {
		if(request.getId() != userId) 
			return "home";
		if(result.hasErrors()) {
			return "director/users/edit_password_user";
		}
		try {
			UserEntity user = userService.findByIdActive(request.getId());
			userService.updatePassword(request.getNewPassword(), request.getNewPassword(), new Date(), this.findCurrentUser(principal), request.getId());
			if(user.getRole() == Role.ROLE_TEACHER)
				return "redirect:/director/profile-teacher"+user.getId();
			if(user.getRole() == Role.ROLE_STUDENT)
				return "redirect:/director/profile-student"+user.getId();
		} catch (NullPointerException e) {
			return "home";
		}
		return "home";
	}
	
	@GetMapping("/edit/profile-user{idUser}")
	public String showEditUserPage(@PathVariable("idUser") int idUser, Model model ) {
		
		UserEntity user = userService.findByIdActive(idUser);
		if(user.getRole() != Role.ROLE_TEACHER && user.getRole() != Role.ROLE_STUDENT) {
			return "home";
		}
		String role = user.getRole()==Role.ROLE_STUDENT?"УЧНІ":"ВЧИТЕЛІ";
		model.addAttribute("role", role);
		UserEditRequest request = UserMapper.userEntetyToUserEditRequest(user);
		model.addAttribute("userEditModel", request);
		List<ClassToStudent> listClasses = classService.findAllActiveClasses();
		if(request.getClassStudent() != null)
			listClasses.remove(request.getClassStudent());
		model.addAttribute("classesListModelForEdit", listClasses);
		List<Subject> listSubjects = new ArrayList<>(Arrays.asList(Subject.values()));
		if(request.getSubject() != null)
			listSubjects.remove(request.getSubject());
		model.addAttribute("subjectEditModel", listSubjects);
		return "director/users/edit_user";
	}
	
	@PostMapping("/edit/profile-user{idUser}")
	public String editUserPage(Principal principal, @ModelAttribute("userEditModel") @Valid UserEditRequest request, 
			BindingResult result, @PathVariable("idUser") int idUser) {
		if(result.hasErrors()) {
			return "director/users/edit_user";
		}
		if(idUser != request.getId())
			return "home";
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			UserEntity entity = UserMapper.userEditRequestToUserEntity(request, currentEntity);
			userService.updateUserByDirector(entity.getLastName(), entity.getFirstName(), entity.getMiddleName(), 
					entity.getEmail(), entity.getBirthDate(), entity.getNameFoto(), new Date(), currentEntity, 
					entity.getSubject(),entity.getClassStudent(), entity.getLogin(), entity.getId());
			if(request.getRole() == Role.ROLE_TEACHER)
				return "redirect:/director/profile-teacher"+idUser;
			if(request.getRole() == Role.ROLE_STUDENT)
				return "redirect:/director/profile-student"+idUser;
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return "home";
	}
	
	@GetMapping("remove/foto/user{userId}") 
	public String removeFoto(Principal principal, @PathVariable("userId") int userId) {
		
		try {
			UserEntity entity = userService.findByIdActive(userId);
			if(entity == null) 
				return "home";
			userService.updateFoto(Constants.USERS_NO_AVATAR, new Date(), findCurrentUser(principal), userId);
			return "redirect:/director/profile-teacher"+userId;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	//Відправити листа на власну пошту
	@GetMapping("/send-password-to-own-email-user{userId}")
	public String showSendPasswordToOwnEmail(Model model, Principal principal, @PathVariable("userId") int userId) {
		try {
			UserEntity userEntity = userService.findByIdActive(userId);
			if(userEntity == null || userEntity.getRole() != Role.ROLE_STUDENT && userEntity.getRole() != Role.ROLE_TEACHER) {
				return "home";
			}
			String role = userEntity.getRole()==Role.ROLE_STUDENT?"УЧНІ":"ВЧИТЕЛІ";
			model.addAttribute("role", role);
			EmailRequest request = UserMapper.userEntityToEmailRequest(userService.findByLoginActive(principal.getName()));
			model.addAttribute("emailModel", request);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
		return "director/users/send_password";
	}
	
	@PostMapping("/send-password-to-own-email-user{userId}")
	public String sendPasswordToOwnEmail(Principal principal, @ModelAttribute("emailModel") @Valid EmailRequest request,
			BindingResult result, Model model, @PathVariable("userId") int userId) {
		if(result.hasErrors()) {
			return "director/users/send_password";
		}
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			UserEntity user = userService.findByIdActive(userId);
			if(!request.getEmail().equals(currentEntity.getEmail()))
				userService.updateEmail(request.getEmail(), new Date(), currentEntity, currentEntity.getId());
			Mail mail = MailManager.createMailToSentPassword(user.getLogin(), user.getPasswordText(), request.getEmail());
			try {
				mailService.sentMessage(mail);
			} catch (MailSendException e) {
				model.addAttribute("notSendModel", "Вибачте, виникла помилка з поштовим сервером, тому лист не відправлено");
				return "director/users/send_password";
			}
			if(user.getRole() == Role.ROLE_TEACHER)
				return "redirect:/director/profile-teacher"+userId;
			if(user.getRole() == Role.ROLE_STUDENT)
				return "redirect:/director/profile-student"+userId;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		return "home";
	}		
		//Відправити листа на пошту	користувача
		@GetMapping("/send-password-to-user{userId}")
		public String showSendPasswordToUserEmail(Model model, Principal principal, @PathVariable("userId") int userId) {
			try {
				UserEntity userEntity = userService.findByIdActive(userId);
				if(userEntity == null || userEntity.getRole() != Role.ROLE_STUDENT && userEntity.getRole() != Role.ROLE_TEACHER) {
					return "home";
				}
				String role = userEntity.getRole()==Role.ROLE_STUDENT?"УЧНІ":"ВЧИТЕЛІ";
				model.addAttribute("role", role);
				EmailRequest request = UserMapper.userEntityToEmailRequest(userService.findByIdActive(userId));
				model.addAttribute("emailUserModel", request);
			} catch (Exception e) {
				e.printStackTrace();
				return "home";
			}
			
			return "director/users/send_password_email_user";
		}
		
		@PostMapping("/send-password-to-user{userId}")
		public String sendPasswordToUserEmail(Principal principal, @ModelAttribute("emailUserModel") @Valid EmailRequest request,
				BindingResult result, Model model,  @PathVariable("userId") int userId) {
			if(result.hasErrors()) {
				return "director/users/send_password_email_user";
			}
			if(request.getId() != userId)
				return "home";
			try {
				UserEntity currentEntity = userService.findByLoginActive(principal.getName());
				UserEntity userEntity = userService.findByIdActive(userId);
				if(userEntity == null || userEntity.getRole() != Role.ROLE_STUDENT && userEntity.getRole() != Role.ROLE_TEACHER) {
					return "home";
				}
				String role = userEntity.getRole()==Role.ROLE_STUDENT?"УЧНІ":"ВЧИТЕЛІ";
				model.addAttribute("role", role);
				if(!request.getEmail().equals(userEntity.getEmail()))
					userService.updateEmail(request.getEmail(), new Date(), currentEntity, request.getId());
				Mail mail = MailManager.createMailToSentPassword(userEntity.getLogin(), userEntity.getPasswordText(), request.getEmail());
				try {
					mailService.sentMessage(mail);
				} catch (MailSendException e) {
					model.addAttribute("notSendModel", "Вибачте, виникла помилка з поштовим сервером, тому лист не відправлено");
					return "director/users/send_password_email_user";
				}
				if(userEntity.getRole() == Role.ROLE_TEACHER)
					return "redirect:/director/profile-teacher"+userId;
				if(userEntity.getRole() == Role.ROLE_STUDENT)
					return "redirect:/director/profile-student"+userId;
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "home";
			} catch (Exception e) {
				e.printStackTrace();
				return "home";
		}
			return "home";
	}
		
		@GetMapping("/remove/user{userId}") 
		public String removeUser(@PathVariable("userId") int userId, Principal principal) {
			
			try {
				UserEntity user = userService.findByIdActive(userId);
				UserEntity currentUser = this.findCurrentUser(principal);
				userService.deleteUser(new Date(), currentUser, userId);
				if(user.getRole() == Role.ROLE_TEACHER)
					return "redirect:/director/teachers/1";
				if(user.getRole() == Role.ROLE_STUDENT)
					return "redirect:/director/students/1";
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "home";
			}
			return "home";
		}
		
		@GetMapping("/remove/foto-user{userId}") 
		public String removeFotoUser(@PathVariable("userId") int userId, Principal principal) {
			
			try {
				UserEntity user = userService.findByIdActive(userId);
				UserEntity currentUser = this.findCurrentUser(principal);
				userService.updateFoto("noAvatar.png", new Date(), currentUser, userId);
				if(user.getRole() == Role.ROLE_TEACHER)
					return "redirect:/director/teachers/1";
				if(user.getRole() == Role.ROLE_STUDENT)
					return "redirect:/director/students/1";
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "home";
			}
			return "home";
		}
}
