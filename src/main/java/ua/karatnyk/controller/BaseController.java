package ua.karatnyk.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.EmailRequest;
import ua.karatnyk.domain.PasswordEditRequest;
import ua.karatnyk.domain.ProfileUserEditRequest;
import ua.karatnyk.domain.ProfileUserViewRequest;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.UserMapper;
import ua.karatnyk.service.MailService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.utilities.Mail;
import ua.karatnyk.service.utilities.MailManager;



@Controller
@SessionAttributes({"userEditModel", "emailModel"})
public class BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@GetMapping({"/", "/login"})
	public String showHomePage() {
		return "home";
	}
	
	
	@GetMapping("/profile")
	public String showUserProfile(Principal principal, Model model) {
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			ProfileUserViewRequest request = UserMapper.userEntityToProfileUserViewRequest(currentEntity);
			model.addAttribute("profileViewModel", request);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
		return "profile";
	}
	
	@GetMapping("/profile/edit")
	public String showUserEditProfile(Principal principal, Model model) {
		
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			ProfileUserEditRequest request = UserMapper.userEntityToProfileUserEditRequest(currentEntity);
			model.addAttribute("userEditModel", request);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
				
		return "profile_edit";
	}
	
	@PostMapping("/profile/edit")
	public String editUserProfile(Principal principal, @ModelAttribute("userEditModel") @Valid ProfileUserEditRequest request, BindingResult result) {
		
		if(result.hasErrors()) {
			return "profile_edit";
		}
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			if(currentEntity.getId() != request.getId())
				return "home";
			UserEntity entity = UserMapper.profileUserEditRequestToUserEntity(request, currentEntity);
				
			userService.updateUser(entity.getLastName(), entity.getFirstName(), 
					entity.getMiddleName(), entity.getEmail(), entity.getBirthDate(), 
					entity.getNameFoto(), new Date(), entity.getLastUpdateByUser(),  entity.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
		return "redirect:/profile";
	}
	
	@GetMapping("/profile/edit-password")
	public String showEditPasswordPage(Principal principal, Model model) {
		
		model.addAttribute("passwordEditModel", new PasswordEditRequest());
		
		return "edit_password";
	}
	
	@PostMapping("/profile/edit-password")
	public String editPassword(Principal principal, @ModelAttribute("passwordEditModel") @Valid PasswordEditRequest request, BindingResult result, Model model) {
		
		try {
			
			UserEntity currentUser = userService.findByLoginActive(principal.getName());
			if(currentUser.getPasswordText().equals(request.getEntered())) {
				if(result.hasErrors()) {
					return "edit_password";
				}
				userService.updatePassword(request.getNewPassword(), request.getNewPassword(), new Date(), currentUser, currentUser.getId());
			}
			else {
				model.addAttribute("errorPass", "Пароль введено не правильно");
				return "edit_password";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "home";	
		}
		return "redirect:/profile";
	}
	
	@GetMapping("/profile/send-email")
	public String showSendPassword(Model model, Principal principal) {
		try {
			EmailRequest request = UserMapper.userEntityToEmailRequest(userService.findByLoginActive(principal.getName()));
			model.addAttribute("emailModel", request);
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
		return "send_password";
	}
	
	@PostMapping("/profile/send-email")
	public String sendPassword(Principal principal, @ModelAttribute("emailModel") @Valid EmailRequest request,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "send_password";
		}
		try {
			UserEntity currentEntity = userService.findByLoginActive(principal.getName());
			if(request.getId() != currentEntity.getId())
				return "home";
			if(!request.getEmail().equals(currentEntity.getEmail()))
				userService.updateEmail(request.getEmail(), new Date(), currentEntity, currentEntity.getId());
			Mail mail = MailManager.createMailToSentPassword(currentEntity.getLogin(), currentEntity.getPasswordText(), request.getEmail());
			try {
				mailService.sentMessage(mail);
			} catch (MailSendException e) {
				model.addAttribute("notSendModel", "Вибачте, виникла помилка з поштовим сервером, тому лист не відправлено");
				return "send_password";
			}
			return "redirect:/profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
	
	}
	
	@GetMapping("/remove/foto") 
	public String removeFoto(Principal principal) {
		
		try {
			UserEntity currentUser = userService.findByLoginActive(principal.getName());
			userService.updateFoto("noAvatar.png", new Date(), currentUser, currentUser.getId());
			return "redirect:/profile";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}

}
