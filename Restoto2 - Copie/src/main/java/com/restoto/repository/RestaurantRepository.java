package com.restoto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restoto.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	@Query(value = "SELECT * FROM Restaurant resto WHERE resto.id_utilisateur=?1",
			nativeQuery = true)
	List<Restaurant> findByUtilisateurId(Integer idUtilisateur);

}
