package com.john.library.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.library.entity.Cart;
import com.john.library.entity.CartItem;
import com.john.library.entity.User;
import com.john.library.repo.CartRepository;
import com.john.library.repo.UserRepository;

@Component
public class CartService {
	
	Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	public Cart getUserCart(long userId) throws Exception {
		Cart cart = null;
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			cart = cartRepo.findByUserIdAndStatus(userId, "ACTIVE");
			if(cart == null) {
				cart = new Cart();
				cart.setUser(user.get());
				cart.setCreatedAt(new Date());
				cart.setStatus("ACTIVE");
				return cartRepo.save(cart);
			} 
			return cart;
		} else {
			logger.error("User not found, while get cart : {}", userId);
			throw new Exception("User not found, while get cart");
		}
	}
	

}
