package com.boly.entity;

import java.io.Serializable; 
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import urlParams.UserRole;

@Entity
public class Client implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private String numtel;
	@Email
	private String email;
	private String adresse;
	@JsonIgnore
	@ManyToOne
	private Tailleur tailleur;
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<MensurationValue> mensurationValues;
	@Transient
	private Long idTailleur;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Collection<MensurationValue> getMensurationValues() {
		return mensurationValues;
	}

	public void setMensurationValues(Collection<MensurationValue> mensurationValues) {
		this.mensurationValues = mensurationValues;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Tailleur getTailleur() {
		return tailleur;
	}
	public void setTailleur(Tailleur tailleur) {
		this.tailleur = tailleur;
	}
	public Long getIdTailleur() {
		return idTailleur;
	}
	public void setIdTailleur(Long idTailleur) {
		this.idTailleur = idTailleur;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
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
	
}
