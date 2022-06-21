package com.restoto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restoto.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	@Query("SELECT user FROM Utilisateur user WHERE user.email =?1")
	Optional<Utilisateur> findByEmail(String email);
	
	@Query("SELECT user FROM Utilisateur user WHERE user.id =?1")
	Optional<Utilisateur> findById(Integer id);


}
