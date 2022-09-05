package com.john.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.library.entity.Book;
import com.john.library.entity.User;
import com.john.library.exception.BookNotFoundException;
import com.john.library.exception.LibraryException;
import com.john.library.model.BookModel;
import com.john.library.repo.BookRepository;
import com.john.library.repo.UserRepository;

@Component
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepo;
	
	public List<Book> getAllBooks() {
	    return bookRepository.findAll();
	}
	
	public Book createBook(BookModel bookModel) {
		User user = userRepo.findByIdAndRole(bookModel.getUserId(), "ADMIN");
		if(user != null) {
			Book book = new Book();
			book.setDescription(bookModel.getDescription());
			book.setTitle(bookModel.getTitle());
			return bookRepository.save(book);
		} else {
			throw new LibraryException("User dont have access to do this action");
		}
	}
	
	public Book getBookById(Long bookId) {
	    return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found : "+bookId));
	}
	
	public Book updateBook(Long bookId,BookModel bookDetails) {
	    Optional<Book> bookFromDb = bookRepository.findById(bookId);
	    User user = userRepo.findByIdAndRole(bookDetails.getUserId(), "ADMIN");
		if(user != null && bookFromDb.isPresent()) {
			if(!bookDetails.getTitle().equals(bookFromDb.get().getTitle()))
				bookFromDb.get().setTitle(bookDetails.getTitle());	
			if(!bookDetails.getDescription().equals(bookFromDb.get().getDescription()))
				bookFromDb.get().setDescription(bookDetails.getDescription());	
			return bookRepository.save(bookFromDb.get());
		} else {
			throw new LibraryException("User dont have access to do this action");
		}
	}
	
	public void deleteBook(Long bookId, Long userId) {
		Optional<Book> bookFromDb = bookRepository.findById(bookId);
	    User user = userRepo.findByIdAndRole(userId, "ADMIN");
		if(user != null && bookFromDb.isPresent()) {
			bookRepository.delete(bookFromDb.get());
		} else {
			throw new LibraryException("User dont have access to do this action");
		}
	}

}
