package com.john.library.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.library.entity.Cart;
import com.john.library.entity.Reservation;
import com.john.library.entity.User;
import com.john.library.exception.LibraryException;
import com.john.library.model.ReservationModel;
import com.john.library.model.ReservationStatusModel;
import com.john.library.repo.CartRepository;
import com.john.library.repo.ReservationRepository;
import com.john.library.repo.UserRepository;
import com.john.library.utils.LibraryUtils;

@Component
public class ReservationService {
	
	Logger logger = LoggerFactory.getLogger(ReservationService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	ReservationRepository reservationRepo;
	
	public Reservation saveReservation(ReservationModel rmodel){
		Optional<User> user = userRepo.findById(rmodel.getUserId());
		if(user.isPresent()) {
			Cart cart = cartRepo.findByUserIdAndStatus(rmodel.getUserId(), "ACTIVE");
			if(cart != null) {
				Reservation reservation = new Reservation();
				reservation.setCart(cart);
				reservation.setUser(user.get());
				reservation.setMessage(rmodel.getRequestMessage());
				reservation.setBookingNumber(LibraryUtils.getBookingNumber());
				reservation.setStatus("PENDING");
				cartRepo.updateCartStatus(cart.getId(), "INACTIVE");
				return reservationRepo.save(reservation);
				
			}
		} else {
			throw new LibraryException("User dont have access to do this action");
		}
		return null;
	}
	
	public Reservation getReservation(String bnumber) {
		return reservationRepo.findByBookingNumber(bnumber);
	}
	
	public List<Reservation> getAllReservation(long userId) {
		return reservationRepo.findByUserId(userId);
	}
	
	public List<Reservation> getAllReservationForAdmin() {
		return reservationRepo.findAll();
	}
	
	public void changeStatus(ReservationStatusModel rModel) {
		User user = userRepo.findByIdAndRole(rModel.getUserId(), "ADMIN");
		if(user!= null) {
			Optional<Reservation> reservation = reservationRepo.findById(rModel.getReservationId());
			if(reservation.isPresent()) {
				reservationRepo.updateStatus(rModel.getReservationId(), rModel.getStatus());
			}
		} else {
			throw new LibraryException("User dont have access to do this action");
		}
		
	}
}
