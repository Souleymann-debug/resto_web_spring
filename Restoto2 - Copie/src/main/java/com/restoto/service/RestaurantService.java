package com.restoto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restoto.model.Restaurant;
import com.restoto.model.Utilisateur;
import com.restoto.repository.RestaurantRepository;
import com.restoto.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@ComponentScan(basePackageClasses = RestaurantRepository.class)
public class RestaurantService {
	public final RestaurantRepository restaurantRepository;
	public final UtilisateurRepository utilisateurRepository;
	
	public List<Restaurant> getRestaurants() {
		return restaurantRepository.findAll() ;
	}
	
	public Restaurant getRestaurant(Integer idRestaurant) {
		return restaurantRepository.findById(idRestaurant).get() ;
	}
	
	public List<Restaurant> getRestaurantByUtilisateur(Integer idUtilisateur) {
		return restaurantRepository.findByUtilisateurId(idUtilisateur) ;
	}

	public void addRestaurant(Restaurant restaurant,Integer utilisateurID) {
		boolean b = utilisateurRepository.existsById(utilisateurID);
		
		if (!b) {
			throw new IllegalStateException("Cet utilisateur n'existe pas !");
		}
		restaurant.setUtilisateur(utilisateurRepository.findById(utilisateurID).get());

		restaurantRepository.save(restaurant);
		
	}


	public void deleteRestaurant(Integer restaurantID) {
		boolean b = restaurantRepository.existsById(restaurantID);
		if(!b) {
			throw new IllegalStateException("Ce restaurant n'existe pas !!");
		}
		restaurantRepository.deleteById(restaurantID);
	}

	@Transactional
	public void updateRestaurant(Restaurant restaurant) {
		restaurantRepository.findById(restaurant.getId())
				.orElseThrow(()-> new IllegalStateException("Ce restaurant n'existe pas !"));
		
		String nom = restaurant.getNom();
		String adresse = restaurant.getAdresse();
		String specialite = restaurant.getSpecialite();
		String siteweb = restaurant.getSiteweb();
		String description = restaurant.getDescription();
		int nbplaces= restaurant.getNbplaces();
		
		if (nom != null && nom.length()>0 && !restaurant.getNom().equals(nom)) {
			restaurant.setNom(nom);
		}
		
		if (adresse != null && adresse.length()>0 && !restaurant.getAdresse().equals(adresse)) {
			restaurant.setAdresse(adresse);
		}
		if (specialite != null && specialite.length()>0 && !restaurant.getSpecialite().equals(specialite)) {
			restaurant.setSpecialite(specialite);
		}
		if (siteweb != null && siteweb.length()>0 && !restaurant.getSiteweb().equals(siteweb)) {
			restaurant.setSiteweb(siteweb);
		}
		if (description != null && description.length()>0 && !restaurant.getDescription().equals(description)) {
			restaurant.setDescription(description);
		}
		if (restaurant.getNbplaces() != nbplaces) {
			restaurant.setNbplaces(nbplaces);
		}
		
		restaurantRepository.save(restaurant);
		
	}
}
