package com.john.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.library.entity.Cart;
import com.john.library.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
	
	Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;

	@GetMapping("/{userId}")
	public ResponseEntity<Cart> getUserCart(@PathVariable("userId") long userId) throws Exception {
		logger.info("add cart : {}", userId);
		Cart cart = cartService.getUserCart(userId);
		return ResponseEntity.status(HttpStatus.OK).body(cart);
	}
	
}