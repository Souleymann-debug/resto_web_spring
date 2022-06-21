package com.restoto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restoto.model.Restaurant;
import com.restoto.model.Utilisateur;
import com.restoto.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurService {
	public final UtilisateurRepository utilisateurRepository;
	
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurRepository.findAll();
	}
	
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		boolean userExists = utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent();
		
		if (userExists) {
			throw new IllegalThreadStateException("Cet email est déjà utilisé");
		}
		
		
		utilisateurRepository.save(utilisateur);
		
	}
	
	public Utilisateur loadUserByUsername(String email) {
		Optional<Utilisateur> userExists = utilisateurRepository.findByEmail(email);
		
		if (!userExists.isPresent()) {
			throw new IllegalStateException("Cet utilisateur n'existe pas");
		}
		
		return userExists.get();
	}
	
	public boolean connexion(String email,String password) {
		boolean auth = false;
		
		Optional<Utilisateur> user = utilisateurRepository.findByEmail(email);
		System.out.println("email : "+email);
		if (!user.isEmpty() && user.get().getPassword().equals(password)) {
			
			System.out.println("password : "+password);
			auth = true;
		} 	
		System.out.println("pass saisi :"+password);
		System.out.println("res : "+auth);
		return auth;
	}
	
	@Transactional
	public void updateUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.findById(utilisateur.getId())
				.orElseThrow(()-> new IllegalStateException("Cet utilisateur n'existe pas !"));
		
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String numtel = utilisateur.getNumtel();
		String adresse = utilisateur.getAdresse();

		
		
		if (nom != null && nom.length()>0 && !utilisateur.getNom().equals(nom)) {
			utilisateur.setNom(nom);
		}
		if (prenom != null && prenom.length()>0 && !utilisateur.getPrenom().equals(prenom)) {
			utilisateur.setPrenom(prenom);
		}
		if (email != null && email.length()>0 && !utilisateur.getEmail().equals(email)) {
			utilisateur.setEmail(email);
		}
		if (numtel != null && numtel.length()>0 && !utilisateur.getNumtel().equals(numtel)) {
			utilisateur.setNumtel(numtel);
		}
		if (adresse != null && adresse.length()>0 && !utilisateur.getAdresse().equals(adresse)) {
			utilisateur.setAdresse(adresse);
		}
		
		
		utilisateurRepository.save(utilisateur);
		
	}
	
}
