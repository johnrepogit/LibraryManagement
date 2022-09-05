package com.john.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.library.entity.Reservation;
import com.john.library.model.ReservationModel;
import com.john.library.model.ReservationStatusModel;
import com.john.library.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/reservation")
	public Reservation createReservation(@RequestBody ReservationModel reservationModel) {
	    return reservationService.saveReservation(reservationModel);
	}
	
	@GetMapping("/reservation/{bnumber}")
	public Reservation getReservation(@PathVariable(value = "bnumber") String bnumber) {
	    return reservationService.getReservation(bnumber);
	}
	
	@GetMapping("/reservation/all/{userId}")
	public List<Reservation> getAllReservation(@PathVariable(value = "userId") long userId) {
	    return reservationService.getAllReservation(userId);
	}
	
	@GetMapping("/reservation")
	public List<Reservation> getAllReservationForAdmin() {
	    return reservationService.getAllReservationForAdmin();
	}
	
	@PutMapping("/reservation")
	public void changeStatusReservation(@RequestBody ReservationStatusModel rModel) {
	    reservationService.changeStatus(rModel);
	}
}
