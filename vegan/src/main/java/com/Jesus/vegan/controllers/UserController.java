package com.Jesus.vegan.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Jesus.vegan.models.User;
import com.Jesus.vegan.services.UserService;
import com.Jesus.vegan.validation.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	
	@GetMapping("/")
	public String index(@ModelAttribute("registration") User user, Model model) {
		return "/users/index.jsp";
	}
	
	@PostMapping("/")
	public String Register(@Valid @ModelAttribute("registration") User user, BindingResult result,  Model model, HttpSession session ) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "redirect:/";
		}
		User newUser = this.uService.registerUser(user);
		session.setAttribute("userId", newUser.getId());
			return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/users/index.jsp";
	}
	
	public Long userId(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return null;
		}
		return (Long)session.getAttribute("userId");
		
	}
		
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirs) {
		if(this.uService.authenticateUser(email, password)) {
			User user = this.uService.findUserByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/foods";
		}
		redirs.addFlashAttribute("error", "Invalid credentials");
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
