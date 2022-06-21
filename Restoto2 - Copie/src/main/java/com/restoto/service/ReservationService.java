package com.restoto.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restoto.model.Reservation;
import com.restoto.model.Restaurant;
import com.restoto.model.Utilisateur;
import com.restoto.repository.ReservationRepository;
import com.restoto.repository.RestaurantRepository;
import com.restoto.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@ComponentScan(basePackageClasses = ReservationService.class)
public class ReservationService {
	
	
	public final ReservationRepository reservationRepository;
	public final RestaurantRepository restaurantRepository;
	public final UtilisateurRepository utilisateurRepository;
	
	public List<Reservation> getReservations() {
		return reservationRepository.findAll() ;
	}
	
	public Reservation getReservation(Integer reservationId) {
		return reservationRepository.findById(reservationId).get();
	}
	
	public List<Reservation> getReservationsByUser(Integer utilisateurId) {
		return reservationRepository.reservationsByUtilisateur(utilisateurId) ;
	}
	
	public List<Reservation> getReservationsByUserNotAccepted(Integer utilisateurId) {
		return reservationRepository.reservationsByUtilisateurNotAccepted(utilisateurId) ;
	}
	
	public void addReservation(Reservation reservation,Integer utilisateurID,Integer restaurantID) throws ParseException {
		
		Optional<Utilisateur> user = utilisateurRepository.findById(utilisateurID);
		Optional<Restaurant> resto = restaurantRepository.findById(restaurantID);
		
		if (!user.isPresent() || !resto.isPresent()) {
			throw new IllegalStateException("Cet utilisateur ou ce restaurant n'existent pas !");
		}
		
		reservation.setUtilisateur(user.get());
		reservation.setRestaurant(resto.get());
		checkAvailability(reservation,resto.get());
		
		if (checkAvailability(reservation, resto.get())) {			
			reservationRepository.save(reservation);			
		}else {
			throw new IllegalStateException("Il n'y a pas assez de places pour cet horaire, veuillez en choisir un autre.");
		}
		
	}
	
	public boolean checkAvailability(Reservation reservation,Restaurant restaurant) throws ParseException {
		
		List<Reservation> allReservations = reservationRepository.reservationsByRestaurant(restaurant.getId());

		int placesLibres = restaurant.getNbplaces();
		
		for (Reservation reservation2 : allReservations) {
			
			System.out.println("date reservation : "+reservation2.getDate());
			if (reservation2.getDate().equals(reservation2.getDate())) {
				
				placesLibres -= reservation2.getNb_personnes();

			}		
		}
		placesLibres -= reservation.getNb_personnes();
		
		if (placesLibres<0) {
			return false;
		}
		
		System.out.println("places libres : "+placesLibres);
		return true;
	}
	
	public void deleteReservation(Integer reservationID) {
		boolean b = reservationRepository.existsById(reservationID);
		if(!b) {
			throw new IllegalStateException("Cette réservation n'existe pas !!");
		}
		reservationRepository.deleteById(reservationID);
	}
	
	@Transactional
	public void updateReservation(Integer reservationID, String date, Integer nb_personnes, String motif, Integer is_accept) {
		Reservation reservation = reservationRepository.findById(reservationID)
				.orElseThrow(()-> new IllegalStateException("Cette réservation n'existe pas !"));
		
		if (date != null && date.length()>0 && !reservation.getDate().equals(date)) {
			reservation.setDate(date);
		}
		
		if (nb_personnes != null  && reservation.getNb_personnes() != nb_personnes) {			
			reservation.setNb_personnes(nb_personnes);
		}
		
		if (motif != null && motif.length()>0 && !reservation.getMotif().equals(motif)) {
			reservation.setMotif(motif);
		}
		
		if (is_accept != null  && reservation.getIs_accept() != is_accept) {
			reservation.setIs_accept(is_accept);
		}
	}
}
