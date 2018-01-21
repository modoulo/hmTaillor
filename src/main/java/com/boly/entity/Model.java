package com.boly.entity;

import java.io.Serializable;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Model implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	private String description;
	@JsonIgnore
	@ManyToOne
	private Catalogue catalogue;
	@Transient
	private Long idCatalogue;
	@ManyToOne
	private TypeDhabit typeDhabit;
	@Transient
	private Long idTypeDhabit;
	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TypeDhabit getTypeDhabit() {
		return typeDhabit;
	}

	public void setTypeDhabit(TypeDhabit typeDhabit) {
		this.typeDhabit = typeDhabit;
	}

	public Long getIdTypeDhabit() {
		return idTypeDhabit;
	}

	public void setIdTypeDhabit(Long idTypeDhabit) {
		this.idTypeDhabit = idTypeDhabit;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	public Long getIdCatalogue() {
		return idCatalogue;
	}
	public void setIdCatalogue(Long idCatalogue) {
		this.idCatalogue = idCatalogue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
