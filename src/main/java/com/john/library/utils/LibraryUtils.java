package com.john.library.utils;

import java.util.Random;

public class LibraryUtils {
	public static String getBookingNumber() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
}
