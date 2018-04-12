package ua.karatnyk.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.karatnyk.domain.NewsAddRequest;
import ua.karatnyk.domain.NewsEditRequest;
import ua.karatnyk.domain.NewsTitleFilter;
import ua.karatnyk.domain.NewsViewRequest;
import ua.karatnyk.entity.News;
import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.NewsMapper;
import ua.karatnyk.service.NewsService;
import ua.karatnyk.service.UserService;
import ua.karatnyk.service.utilities.Constants;
import ua.karatnyk.service.utilities.FileManager;

@Controller
@RequestMapping("/director")
@SessionAttributes({"newsEditModel", "nameFoto"})
public class DirectorNewsController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/news/{pageNumber}")
	public String showNewsTablePage(Model model, @PathVariable("pageNumber") int pageNumber) {
		
		Page<News> page = newsService.getPagebleNews(pageNumber, 6, "DESC", "createdAt");
		int currentPage = page.getNumber()+1;
		int begin = Math.max(1, currentPage-1);
		int end = Math.min(begin+10, page.getNumber());
		
		model.addAttribute("newsList", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", currentPage);
		model.addAttribute("newsListByPageSize", NewsMapper.listNewsToListNewsViewRequest(page.getContent()));
		model.addAttribute("flag", true);
		return "director/news/view_news";
	}
	
	@GetMapping("/search/news/{pageNumber}") 
	public String makeFilterShowPage(Model model, @PathVariable("pageNumber") int pageNumber, @RequestParam("search") String filter) {
		
		List<News> list = newsService.getNewsWithTitleFilter(new NewsTitleFilter(filter));
		model.addAttribute("newsListByPageSize", NewsMapper.listNewsToListNewsViewRequest(list));
		model.addAttribute("flag", true);
		return "director/news/view_news";
	}
	
	@GetMapping("/remove_filter/news") 
	public String removeFilter() {
		
		return "redirect:/director/news/1";
	}
	
	@GetMapping("/add_news")
	public String showAddNewsPage(Model model) {
		
		model.addAttribute("newsRequestModel", new NewsAddRequest());	
		return "director/news/add_news";
	}
	
	@PostMapping("/add_news")
	public String addNewsPage(@ModelAttribute("newsRequestModel") @Valid NewsAddRequest request, 
			BindingResult result, Principal principal) throws IOException  {
		
		if(result.hasErrors()) {
			return "director/news/add_news";
		}
		try {
			News news = NewsMapper.addRequestToNews(request, getCurrentUser(principal));
			newsService.saveNews(news);
			if(!request.getFile().isEmpty())
				FileManager.savePhotoUserInProject(request.getFile(), userService.findByLoginActive(principal.getName()).getId(), Constants.FOLDER_FOR_USER_NEWS_IMAGES);
			return "redirect:/director/news/1";
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	
	}
	
	@GetMapping("/profile/news{newsId}")
	public String showNewsProfilePage(Model model, @PathVariable("newsId") String newsId) {
		News news = newsService.findById(Integer.parseInt(newsId));
		NewsViewRequest request = NewsMapper.newsToViewRequest(news);	
		model.addAttribute("newsModel", request);
		return "director/news/profile_news";
	}
	
	@GetMapping("/edit/news{newsId}")
	public String showEditNewsPage(Model model, @PathVariable("newsId") int newsId) {
		News news = newsService.findById(newsId);
		NewsEditRequest request = NewsMapper.newsToNewsEditRequest(news);
		model.addAttribute("newsEditModel", request);
		model.addAttribute("nameFoto", request.getNameImage());
		return "director/news/edit_news";
	}
	
	@PostMapping("/edit/news{newsId}")
	public String editNewsPage(@ModelAttribute("newsEditModel") @Valid NewsEditRequest request,
			BindingResult result, Principal principal) throws IOException{
		if(result.hasErrors()) {
			return "director/news/edit_news";
		}
		try {
			News news = NewsMapper.editNewsRequestToNews(request, getCurrentUser(principal));
			newsService.saveNews(news);
			if(!request.getFile().isEmpty())
				FileManager.savePhotoUserInProject(request.getFile(), userService.findByLoginActive(principal.getName()).getId(), Constants.FOLDER_FOR_USER_NEWS_IMAGES);
			
			return "redirect:/director/profile/news"+request.getId();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
	@GetMapping("/remove/news{newsId}") 
	public String removeNewsIs(@PathVariable("newsId") String newsId) {
		//FileManager.deleteFileFromProject(newsService.findById(Integer.parseInt(newsId)).getPathToFoto());
		newsService.deleteNewsById(Integer.parseInt(newsId));
		return "redirect:/director/news/1";
	}
	
	@GetMapping("remove/foto/news{newsId}") 
	public String removeFoto(Principal principal, @PathVariable("newsId") int newsId) {
		
		try {
			News news = newsService.findById(newsId);
			if(news == null) 
				return "home";
			newsService.updateFoto(Constants.NEWS_NO_AVATAR, new Date(), getCurrentUser(principal), newsId);
			return "redirect:/director/profile/news"+newsId;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "home";
		}
	}
	
	private UserEntity getCurrentUser(Principal principal) {
		return userService.findByLoginActive(principal.getName());
	}


}
