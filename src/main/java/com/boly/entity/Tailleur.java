package com.boly.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import urlParams.UserRole;

@Entity
public class Tailleur implements Serializable{

		
		@Id
		@GeneratedValue
		private Long id;
		@NotNull
		private String nom;
		@NotNull
		private String prenom;
		@Enumerated(EnumType.STRING)
		private Sexe sexe;
		private String adresse;
		@NotNull
		@Email
		private String email;
		private String motDePasse;
		private String numtel;
		@OneToMany(mappedBy="tailleur", cascade = CascadeType.ALL, orphanRemoval=true)
		private Collection<Client> clients;
		@OneToMany(mappedBy="tailleur", cascade = CascadeType.ALL, orphanRemoval=true)
		private Collection<Catalogue> catalogues;
		
		public Tailleur() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Tailleur(String nom, String prenom, Date dateDeNaissance, String lieuDeNaissance, String email,
				String numtel, String numCartEtudiant, int anneeDetude) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.numtel = numtel;
		}

		
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
		@JsonIgnore
		public boolean isMotDePasseNull() {
			return this.motDePasse == null || this.motDePasse == "";
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		public boolean verifieMotDePasse(String motDePasse) {
			if (motDePasse==null) {
				return false;
			}
			if (motDePasse.equals(this.motDePasse)) {
				return true;
			}
			return false;
		}
		@JsonIgnore
		public UserRole getIdentiy() {
			return new UserRole(this.id, Role.Tailleur);
		}
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getNumtel() {
			return numtel;
		}
		public void setNumtel(String numtel) {
			this.numtel = numtel;
		}
		public Sexe getSexe() {
			return sexe;
		}
		public void setSexe(Sexe sexe) {
			this.sexe = sexe;
		}
		public Collection<Client> getClients() {
			return clients;
		}
		public void setClients(Collection<Client> clients) {
			this.clients = clients;
		}
		public Collection<Catalogue> getCatalogues() {
			return catalogues;
		}
		public void setCatalogues(Collection<Catalogue> catalogues) {
			this.catalogues = catalogues;
		}
		

	}

