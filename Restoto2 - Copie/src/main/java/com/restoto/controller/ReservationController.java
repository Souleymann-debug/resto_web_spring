package com.restoto.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoto.model.Reservation;
import com.restoto.service.ReservationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ReservationController {
	
	@Autowired 
	private ReservationService reservationService;
	
	public ReservationController() {
		
	}
	
	@GetMapping(path="api/reservations")
	public List<Reservation> getReservations() {
		return reservationService.getReservations();
	}
	
	@GetMapping(path="api/reservations/utilisateur/{utilisateurId}")
	public List<Reservation> getReservationsByUser(@PathVariable("utilisateurId") Integer utilisateurId) {
		return reservationService.getReservationsByUser(utilisateurId);
	}
	
	@GetMapping(path="api/reservations/notaccepted/utilisateur/{utilisateurId}")
	public List<Reservation> getReservationsByUserNotAccepted(@PathVariable("utilisateurId") Integer utilisateurId) {
		return reservationService.getReservationsByUserNotAccepted(utilisateurId);
	}
	
	@GetMapping(path="api/reservation/{reservationId}")
	public Reservation getReservation(@PathVariable("reservationId") Integer reservationId) {
		return reservationService.getReservation(reservationId);
	}
	
	@PostMapping(path="api/reservation/ajouter/{utilisateurID}/{restaurantID}")
	public void addReservation(
			@RequestBody Reservation reservation,
			@PathVariable("utilisateurID") Integer utilisateurID,
			@PathVariable("restaurantID") Integer restaurantID
			) {
		try {
			reservationService.addReservation(reservation,utilisateurID,restaurantID);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DeleteMapping(path="api/reservation/supprimer/{reservationID}")
	public void deleteReservation(@PathVariable("reservationID") Integer reservationID) {
		reservationService.deleteReservation(reservationID);
	}
	
	@PutMapping(path="api/reservation/modifier/{reservationID}")
	public void updateReservation(
			@PathVariable("reservationID") Integer utilisateurID,
			@RequestParam(required = false) String date,
			@RequestParam(required = false) Integer nb_personnes,
			@RequestParam(required = false) String motif,
			@RequestParam(required = false) Integer is_accept
			) {
		reservationService.updateReservation(utilisateurID,date,nb_personnes, motif, is_accept);
	}
}
