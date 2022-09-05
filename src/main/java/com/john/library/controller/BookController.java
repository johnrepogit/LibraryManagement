package com.john.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.library.entity.Book;
import com.john.library.model.BookModel;
import com.john.library.service.BookService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBooks() {
	    return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody BookModel book) {
	    return ResponseEntity.ok(bookService.createBook(book));
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId) {
	    return ResponseEntity.ok(bookService.getBookById(bookId));
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId,
	                                         @RequestBody BookModel bookDetails) {
	    return ResponseEntity.ok(bookService.updateBook(bookId, bookDetails));
	}
	
	@DeleteMapping("/book/{id}/{userId}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId, @PathVariable(value = "userId") Long userId) {
		bookService.deleteBook(bookId,userId);
	    return ResponseEntity.ok().build();
	}

}
