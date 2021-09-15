package com.Jesus.vegan.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Jesus.vegan.models.Food;
import com.Jesus.vegan.models.User;
import com.Jesus.vegan.services.FoodService;
import com.Jesus.vegan.services.PictureService;
import com.Jesus.vegan.services.UserService;

@Controller
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	private FoodService fService;
	@Autowired
	private UserService uService;
	@Autowired
	private PictureService pService;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
	
	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return null;
		}
		return (Long)session.getAttribute("userId");
	}
	
	private String now() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
		return df.format(new Date());
	}
	
	@GetMapping("")
	public String Index(@ModelAttribute("food") Food food, Model viewModel, HttpSession session) {
		Long userId = this.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		}
		User user = this.uService.findUserById(userId);
		viewModel.addAttribute("usersFoods", this.fService.getAllRecipes());
		viewModel.addAttribute("user", user);
		viewModel.addAttribute("now", now());
		return "foods/index.jsp";
	}
	
	@PostMapping("")
	public String Create(@Valid @ModelAttribute("food") Food food, BindingResult result, Model viewModel, HttpSession session, @RequestParam("title") String title, @RequestParam("recipe") String recipe) {
		if(result.hasErrors()) {
			Long userId = this.userSessionId(session);
			User user = this.uService.findUserById(userId);
			viewModel.addAttribute("user", user);
			viewModel.addAttribute("now", now());
			return "foods/index.jsp";
		}
		Long userId = this.userSessionId(session);
		User user = this.uService.findUserById(userId);
		food.setFoodie(user);
		this.fService.create(food);
		return "redirect:/foods";
	}
	
	@PostMapping("/upload")
	public String upload(@RequestParam("pic") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr) {
		User user = this.uService.findUserById((Long)session.getAttribute("userId"));
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message", "cannot upload picture");
			return "redirect:/foods";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String url = "/img/" + file.getOriginalFilename();
			this.pService.uploadPic(url);
		}catch (IOException e){
			e.printStackTrace();
		}
		return "redirect:/foods";
	}
	
	@GetMapping("/{id}")
	public String Show(@PathVariable("id") Long id, Model viewModel, HttpSession session) {
		Long userId = this.userSessionId(session);
		User user = this.uService.findUserById(userId);
		Food food = this.fService.findId(id);
		if(userId == null) {
			return "redirect:/";
		}
		if(food == null) {
			return "redirect:/foods";
		}
		viewModel.addAttribute("food", food);
		viewModel.addAttribute("userId", userId);
		viewModel.addAttribute("user", user);
		return "/foods/show.jsp";
	}
	
	@PutMapping("/{id}")
	public String Update(@Valid @ModelAttribute("food") Food food, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model viewModel) {
		Long userId = this.userSessionId(session);
		User user = this.uService.findUserById(userId);
		Food comida = this.fService.findId(food.getId());
		if(result.hasErrors()) {
			System.out.println("Error");
			viewModel.addAttribute("food", comida);
			viewModel.addAttribute("userId", userId);
			viewModel.addAttribute("user", user);
			return "/foods/show.jsp";
		}
		this.fService.update(food);
		return "redirect:/foods";
	}
		
	@DeleteMapping("/delete/{id}")
	public String Delete(@PathVariable("id") Long id) {
		this.fService.delete(id);
		return "redirect:/foods";
	}
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		Long foodId = id;
		User liker = this.uService.findUserById(userId);
		Food likedFood = this.fService.findId(foodId);
		this.fService.addLiker(liker, likedFood);
		return "redirect:/foods";
		
	}
	
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		Long foodId = id;
		User liker = this.uService.findUserById(userId);
		Food likedFood = this.fService.findId(foodId);
		this.fService.removeLiker(liker, likedFood);
		return "redirect:/foods";
		
	}

}
