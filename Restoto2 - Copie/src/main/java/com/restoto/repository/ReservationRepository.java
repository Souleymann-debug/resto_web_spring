package com.restoto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restoto.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query(
			value = "SELECT * FROM Reservation resto WHERE resto.id_resto =?1",
			nativeQuery = true
	)
	List<Reservation> reservationsByRestaurant(Integer id_resto);

	@Query(value = "SELECT * FROM Reservation reserv,Restaurant resto WHERE resto.id_utilisateur=?1 and reserv.id_resto=resto.id and reserv.is_accept=1",
			nativeQuery = true)
	List<Reservation> reservationsByUtilisateur(Integer idUtilisateur);
	
	
	@Query(value = "SELECT * FROM Reservation reserv,Restaurant resto WHERE resto.id_utilisateur=?1 and reserv.id_resto=resto.id and reserv.is_accept=0",
			nativeQuery = true)
	List<Reservation> reservationsByUtilisateurNotAccepted(Integer idUtilisateur);
	
}
