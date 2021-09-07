package com.Jesus.vegan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jesus.vegan.models.Food;
import com.Jesus.vegan.models.User;
import com.Jesus.vegan.repositories.FoodRepository;

@Service
public class FoodService {
	@Autowired
	private FoodRepository fRepo;
	
	public List<Food> getAllRecipes(){
		return this.fRepo.findAll();
	}
	
	public Food findId(Long id) {
		return this.fRepo.findById(id).orElse(null);
	}
	
	public Food create(Food food) {
		return this.fRepo.save(food);
	}
	
	public Food update(Food food) {
		return this.fRepo.save(food);
	}
	
	public void delete(Long id) {
		this.fRepo.deleteById(id);
	}
	
	public void addLiker(User user, Food food) {
		List<User> likers = food.getLikers();
		likers.add(user);
		this.fRepo.save(food);
	}
	
	public void removeLiker(User user, Food food) {
		List<User> likers = food.getLikers();
		likers.remove(user);
		this.fRepo.save(food);
	}
}
