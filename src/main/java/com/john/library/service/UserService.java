package com.john.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.library.entity.User;
import com.john.library.exception.LibraryException;
import com.john.library.model.SignInModel;
import com.john.library.model.SignUpModel;
import com.john.library.repo.UserRepository;

@Component
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepo;
	
	public User signIn(SignInModel model) {
		User user = userRepo.findByEmailAndPassword(model.getUsername(), model.getPassword());
		if(user != null) {
			return user;
		} else {
			throw new LibraryException("User not found");
		}
	}
	
	public User signUp(SignUpModel model) {
		User user = userRepo.findByEmail(model.getEmail());
		if(user == null) {
			user = new User(model.getEmail(), model.getPassword(), model.getFirstName(), model.getLastName(), model.getRole());
			return userRepo.save(user);
		} else {
			throw new LibraryException("Email already registered");
		}
	}
	
	

	
}
