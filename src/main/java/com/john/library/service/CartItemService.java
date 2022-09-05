package com.john.library.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.library.entity.Book;
import com.john.library.entity.Cart;
import com.john.library.entity.CartItem;
import com.john.library.entity.User;
import com.john.library.exception.LibraryException;
import com.john.library.model.CartItemModel;
import com.john.library.repo.BookRepository;
import com.john.library.repo.CartItemRepository;
import com.john.library.repo.CartRepository;
import com.john.library.repo.UserRepository;

@Component
public class CartItemService {
Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	CartItemRepository cartItemRepo;
	
	@Autowired
	BookService bookService;
	
	
	public Cart addItemInCart(CartItemModel cartItemModel) {
		Cart cart = null;
		Optional<User> user = userRepo.findById(cartItemModel.getUserId());
		cart = cartRepo.findByUserIdAndStatus(cartItemModel.getUserId(), "ACTIVE");
		if(user.isPresent() && cart != null) {
			Book book = bookService.getBookById(cartItemModel.getBookId());
			CartItem item = new CartItem();
			item.setActive(true);
			item.setBook(book);
			item.setCart(cart);
			Set<CartItem> cartItemSet = cart.getCartItemSet();
			List<Long> bookIdList = cartItemSet.stream().map(each -> each.getBook().getId()).collect(Collectors.toList());
			if(!bookIdList.contains(book.getId())) {
				cartItemSet.add(item);
			}
			return cartRepo.save(cart);
		} else {
			logger.error("User not found, while add item in cart : {}", cartItemModel.getUserId());
			throw new LibraryException("User not found, while add item in cart");
		}
	}
	
	public Cart removeItemInCart(CartItemModel cartItemModel) {
		Cart cart = null;
		Optional<User> user = userRepo.findById(cartItemModel.getUserId());
		cart = cartRepo.findByUserIdAndStatus(cartItemModel.getUserId(), "ACTIVE");
		if(user.isPresent() && cart != null) {
			Book book = bookService.getBookById(cartItemModel.getBookId());
			Set<CartItem> cartItemSet = cart.getCartItemSet();
			CartItem deleteItem = null;
			for(CartItem item : cartItemSet) {
				if(item.getBook().getId()==book.getId()) {
					deleteItem = item;
					item.setCart(null);
					System.out.println("=item.getId()=="+item.getId());
					cartItemRepo.deleteById(item.getId());
				}
			}
			cart.getCartItemSet().remove(deleteItem);
			return cartRepo.save(cart);
		} else {
			logger.error("User not found, while add item in cart : {}", cartItemModel.getUserId());
			throw new LibraryException("User not found, while add item in cart");
		}
	}
	
}
