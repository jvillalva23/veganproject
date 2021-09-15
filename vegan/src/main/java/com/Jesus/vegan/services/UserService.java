package com.Jesus.vegan.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jesus.vegan.models.User;
import com.Jesus.vegan.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository uRepo;
	
	public User findUserById(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	
	public List<User> getAllUser(){
		return this.uRepo.findAll();
	}
	
	public boolean emailExist(String email) {
		return uRepo.existsByEmail(email);
	}
	
	public User findUserByEmail(String email) {
		return uRepo.findByEmail(email);
	}
	
	public void saveUser(User user) {
		this.uRepo.save(user);
	}
	
	public User registerUser(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return uRepo.save(user);
	}

	public boolean authenticateUser(String email, String password) {
		User user = uRepo.findByEmail(email);
		
		if(user == null) {
			return false;
		}
		else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public void deleteUser(User user) {
		uRepo.delete(user);
	}
}