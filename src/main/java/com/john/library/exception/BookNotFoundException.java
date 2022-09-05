package com.john.library.exception;
// this throws exception out when the book is not found
public class BookNotFoundException extends RuntimeException{

	public BookNotFoundException(String message) {
		super(message);
	}
	
}
