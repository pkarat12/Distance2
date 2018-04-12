package ua.karatnyk.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ua.karatnyk.entity.ClassToStudent;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.enumerations.Grade;
import ua.karatnyk.enumerations.LetterToGrade;
import ua.karatnyk.enumerations.Role;
import ua.karatnyk.enumerations.Subject;
import ua.karatnyk.service.ClassToStudentService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.utilities.Constants;

@Controller
public class AddDataController {
	
	@Autowired
	private ClassToStudentService classService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/add/classes")
	public String addClasses(Principal principal) {
		
		try {
			for(int i = 0; i<Grade.values().length; i++) {
				for(int j =0; j<LetterToGrade.values().length-3; j++) {
					ClassToStudent classToStudent = new ClassToStudent();
					classToStudent.setGrade(Grade.values()[i]);
					classToStudent.setLetterToGrade(LetterToGrade.values()[j]);
					classToStudent.setCreatedByUser(getCurrentUser(principal));
					classToStudent.setLastUpdateByUser(getCurrentUser(principal));
					classService.saveClassToStudent(classToStudent);
				}
			}
			return "home";
		} catch (NullPointerException e) {
			return "home";
		}
	}
	
	@GetMapping("add/teachers")
	public String addteachers(Principal principal) {
		
		List<String> teachers = convertFile("teacher.txt");
		for(int i=0; i<teachers.size(); i++) {
			String [] teacherName = teachers.get(i).split(" ");
			UserEntity entity = new UserEntity();
			entity.setCreatedByUser(getCurrentUser(principal));
			entity.setFirstName(teacherName[2]);
			entity.setLastName(teacherName[1]);
			entity.setLastUpdateByUser(getCurrentUser(principal));
			entity.setLogin(createRandomString(15));
			entity.setNumberSchool(getCurrentUser(principal).getNumberSchool());
			entity.setNameFoto(Constants.USERS_NO_AVATAR);
			entity.setMiddleName(teacherName[3]);
			entity.setPassword("1111");
			entity.setPasswordText("1111");
			entity.setRole(Role.ROLE_TEACHER);
			entity.setSubject(Subject.values()[(int) (Math.random()*7)]);
			userService.saveUser(entity);
		}
		UserEntity entity = new UserEntity();
		entity.setCreatedByUser(getCurrentUser(principal));
		entity.setFirstName("Каратник");
		entity.setLastName("Катерина");
		entity.setLastUpdateByUser(getCurrentUser(principal));
		entity.setLogin("teacher");
		entity.setNumberSchool(getCurrentUser(principal).getNumberSchool());
		entity.setNameFoto(Constants.USERS_NO_AVATAR);
		entity.setMiddleName("Сергіївна");
		entity.setPassword("1111");
		entity.setPasswordText("1111");
		entity.setRole(Role.ROLE_TEACHER);
		entity.setSubject(Subject.INFORMATIC);
		userService.saveUser(entity);
		return "home";
	}
	
	@GetMapping("add/students")
	public String addStudents(Principal principal) {
		
		List<String> students = convertFile("students.txt");
		List<ClassToStudent> classToStudents = classService.findAllActiveClasses();
		for(int i=0; i<students.size(); i++) {
			String [] teacherName = students.get(i).split(" ");
			UserEntity entity = new UserEntity();
			entity.setCreatedByUser(getCurrentUser(principal));
			entity.setFirstName(teacherName[1]);
			entity.setLastName(teacherName[0]);
			entity.setLastUpdateByUser(getCurrentUser(principal));
			entity.setLogin(createRandomString(25));
			entity.setNumberSchool(getCurrentUser(principal).getNumberSchool());
			entity.setNameFoto(Constants.USERS_NO_AVATAR);
			entity.setMiddleName(teacherName[2]);
			entity.setPassword("1111");
			entity.setPasswordText("1111");
			entity.setRole(Role.ROLE_STUDENT);
			entity.setClassStudent(classToStudents.get((int) (Math.random()*classToStudents.size())));
			userService.saveUser(entity);
		}
		
		UserEntity entity = new UserEntity();
		entity.setCreatedByUser(getCurrentUser(principal));
		entity.setFirstName("Власюк");
		entity.setLastName("Ірина");
		entity.setLastUpdateByUser(getCurrentUser(principal));
		entity.setLogin("pupil");
		entity.setNumberSchool(getCurrentUser(principal).getNumberSchool());
		entity.setNameFoto(Constants.USERS_NO_AVATAR);
		entity.setPassword("1111");
		entity.setPasswordText("1111");
		entity.setRole(Role.ROLE_STUDENT);
		entity.setClassStudent(classToStudents.get((int) (Math.random()*classToStudents.size())));
		userService.saveUser(entity);
		
		return "home";
	}
	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}
	
	public static List<String> convertFile(String nameFile) {
		String pathFile = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+
				File.separator+"webapp";
		try {
			List<String> s = Files.lines(Paths.get(pathFile+File.separator+nameFile), StandardCharsets.UTF_8).collect(Collectors.toList());
			return s;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String createRandomString(int length) {
		String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(mCHAR.length());
	        char ch = mCHAR.charAt(number);
	        builder.append(ch);
	    }
	    return builder.toString();
	}

	
	/*public static void main(String[] args) {
		
		System.out.println(convertFile("students.txt").size());
		for()
		
	}*/

}
