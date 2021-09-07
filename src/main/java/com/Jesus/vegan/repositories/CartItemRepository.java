package com.Jesus.vegan.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Jesus.vegan.models.CartItem;
import com.Jesus.vegan.models.User;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	List<CartItem> findAll();
	List<CartItem> findByUser(User user);
}
