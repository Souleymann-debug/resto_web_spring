package com.restoto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoto.model.Restaurant;
import com.restoto.service.RestaurantService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class RestaurantController {
private RestaurantService restaurantService;
	
	@GetMapping(path="restaurants")
	public List<Restaurant> getRestaurants() {
		return restaurantService.getRestaurants();
	}
	
	@GetMapping(path="restaurant/{restaurantID}")
	public Restaurant getRestaurant(@PathVariable("restaurantID") Integer restaurantID) {
		return restaurantService.getRestaurant(restaurantID);
	}
	
	@GetMapping(path="restaurant/utilisateur/{idUtilisateur}")
	public List<Restaurant> getRestaurantByUtilisateur(@PathVariable("idUtilisateur") Integer idUtilisateur) {
		return restaurantService.getRestaurantByUtilisateur(idUtilisateur);
	}
	
	@PostMapping(path="restaurant/ajouter/{utilisateurID}")
	public void addRestaurant(
								@RequestBody Restaurant restaurant,
								@PathVariable("utilisateurID") Integer utilisateurID
								) {
		restaurantService.addRestaurant(restaurant,utilisateurID);
	}
	
	@DeleteMapping(path="restaurant/supprimer/{restaurantID}")
	public void deleteRestaurant(@PathVariable("restaurantID") Integer restaurantID) {
		restaurantService.deleteRestaurant(restaurantID);
	}
	
	@PutMapping(path="restaurant/modifier/")
	public void updateRestaurant(@RequestBody Restaurant restaurant) {
		restaurantService.updateRestaurant(restaurant);
	}
}
