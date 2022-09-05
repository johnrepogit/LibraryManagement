package com.john.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.library.entity.User;
import com.john.library.model.SignInModel;
import com.john.library.model.SignUpModel;
import com.john.library.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<User> signId(@RequestBody SignInModel sModel) {
		System.out.println("==========login===========>");
		logger.info("Usercontroller: login API triggered - username: {}",sModel.getUsername());
		return ResponseEntity.ok(userService.signIn(sModel));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> saveUser(@RequestBody SignUpModel sModel){
		logger.info("Usercontroller: register API triggered - username: {}",sModel.getEmail());
		return ResponseEntity.ok(userService.signUp(sModel));
	}
}
