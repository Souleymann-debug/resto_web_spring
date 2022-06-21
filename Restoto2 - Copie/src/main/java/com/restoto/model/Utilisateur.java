package com.restoto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "numtel")
	private String numtel;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "isadmin")
	private Boolean isadmin;
	
	
	public Utilisateur(String nom, String prenom, String email, String password, String numtel, String adresse,
			Boolean isadmin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.numtel = numtel;
		this.adresse = adresse;
		this.isadmin = isadmin;
	}
	
	
}
