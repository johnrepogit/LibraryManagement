package com.john.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.library.entity.Cart;
import com.john.library.model.CartItemModel;
import com.john.library.service.CartItemService;

@RestController
@RequestMapping("/api/cart-item")
@CrossOrigin(origins = "*")
public class CartItemController {
	
	Logger logger = LoggerFactory.getLogger(CartItemController.class);
	
	@Autowired
	CartItemService cartItemService;

	@PostMapping("/add")
	public ResponseEntity<Cart> addItemInCart(@RequestBody CartItemModel cartItemModel) {
		logger.info("add cart item : {}", cartItemModel.getUserId());
		Cart cart = cartItemService.addItemInCart(cartItemModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(cart);
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Cart> removeItemInCart(@RequestBody CartItemModel cartItemModel) {
		logger.info("add cart item : {}", cartItemModel.getUserId());
		Cart cart = cartItemService.removeItemInCart(cartItemModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(cart);
	}
}
