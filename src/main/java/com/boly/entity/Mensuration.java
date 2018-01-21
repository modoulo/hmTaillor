package com.boly.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Mensuration implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	@JsonIgnore
	@OneToMany(mappedBy="mensuration", cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<MensurationValue> mensurationValues;
	public Mensuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Collection<MensurationValue> getMensurationValues() {
		return mensurationValues;
	}

	public void setMensurationValues(Collection<MensurationValue> mensurationValues) {
		this.mensurationValues = mensurationValues;
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
