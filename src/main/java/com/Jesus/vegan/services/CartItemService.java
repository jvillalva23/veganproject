package com.Jesus.vegan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jesus.vegan.models.CartItem;
import com.Jesus.vegan.models.User;
import com.Jesus.vegan.repositories.CartItemRepository;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cRepo;
	
	public List<CartItem> getAllItems() {
		return this.cRepo.findAll();
	}
	
	public List<CartItem> listCartItems(User user){
		return this.cRepo.findByUser(user);
	}
	
	public CartItem findById(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	
	public CartItem createCartItem(CartItem cartItem) {
		return this.cRepo.save(cartItem);
	}
	
	public CartItem updateCartItem(CartItem cartItem) {
		return this.cRepo.save(cartItem);
	}
	
	public void deleteCartItem(Long id) {
		this.cRepo.deleteById(id);
	}
}
