package com.Jesus.vegan.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Jesus.vegan.models.CartItem;
import com.Jesus.vegan.models.User;
import com.Jesus.vegan.services.CartItemService;
import com.Jesus.vegan.services.FoodService;
import com.Jesus.vegan.services.UserService;

@Controller
public class CartItemController {
	
	@Autowired
	private CartItemService cService;
	@Autowired
	private FoodService fService;
	@Autowired
	private UserService uService;
	
	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return null;
		}
		return (Long)session.getAttribute("userId");
	}
	
	@GetMapping("/foods/cart")
	public String showCartItem(Model viewModel, HttpSession session) {
		Long userId = this.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		}
		User user = this.uService.findUserById(userId);
		List<CartItem> cartItems = cService.listCartItems(user);
		viewModel.addAttribute("cartItems", cartItems);
		viewModel.addAttribute("pageTitle" , "Shopping Cart");
		return "foods/showcart.jsp";
	}

}
