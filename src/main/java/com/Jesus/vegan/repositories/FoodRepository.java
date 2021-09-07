package com.Jesus.vegan.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Jesus.vegan.models.Food;

public interface FoodRepository extends CrudRepository<Food, Long>{
	List<Food> findAll();

}
