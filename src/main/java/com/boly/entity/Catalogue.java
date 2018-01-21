package com.boly.entity;

import java.io.Serializable; 
import java.util.Collection;
import java.util.Date;
import java.util.Random;

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
public class Catalogue implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	@JsonIgnore
	@ManyToOne
	private Tailleur tailleur;
	@Transient
	private Long idTailleur;
	public Catalogue() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
