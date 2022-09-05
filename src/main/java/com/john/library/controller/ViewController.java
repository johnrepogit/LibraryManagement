package com.john.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class ViewController {

	@GetMapping(value = {"/","/login"})
	public String showLoginForm() {
		return "/login";
	}
	
	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		return "register";
	}
	
	@GetMapping(value = "/home")
	public String home(Model model) {
		return "home";
	}
	
	@GetMapping(value = "/cart")
	public String cart(Model model) {
		return "cart";
	}
	
	@GetMapping(value = "/books")
	public String books(Model model) {
		return "books";
	}
	
	@GetMapping(value = "/reserve")
	public String reserve(Model model) {
		return "reserve";
	}
}
