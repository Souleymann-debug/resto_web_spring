package com.restoto.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restoto.model.Connexion;
import com.restoto.model.Restaurant;
import com.restoto.model.Utilisateur;
import com.restoto.service.UtilisateurService;

import lombok.AllArgsConstructor;

@CrossOrigin()
@RestController
@AllArgsConstructor
public class UtilisateurController {
	private UtilisateurService utilisateurService;

    @GetMapping(path = "utilisateurs")
    public List<Utilisateur> getAll() {
        return utilisateurService.getUtilisateurs();
    }
	
    @GetMapping(path = "utilisateur/{utilisateurEmail}")
    public Utilisateur getByEmail(@PathVariable("utilisateurEmail") String utilisateurEmail) {
        return utilisateurService.loadUserByUsername(utilisateurEmail);
    }
    
    @PostMapping(path = "utilisateur/connexion")
    public boolean connexion(@RequestBody Connexion connexion) {
        return utilisateurService.connexion(connexion.getEmail(),connexion.getPassword());
    }
    
	@PostMapping(path="utilisateur/ajouter")
	public Utilisateur addRestaurant(@RequestBody Utilisateur utilisateur) {
		utilisateurService.ajouterUtilisateur(utilisateur);
		return utilisateur;
	}
	
	@PutMapping(path="utilisateur/modifier")
	public Utilisateur modifUser(@RequestBody Utilisateur utilisateur) {
		utilisateurService.updateUtilisateur(utilisateur);
		return utilisateur;
	}
}
