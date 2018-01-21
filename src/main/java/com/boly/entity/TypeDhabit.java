package com.boly.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;



@Entity
public class TypeDhabit implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	@ManyToMany
	private Collection<Mensuration> mensurations;
	@OneToMany(mappedBy="typeDhabit", cascade=CascadeType.ALL,orphanRemoval=true)
	private Collection<Model> models;
	
	public TypeDhabit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Collection<Mensuration> getMensurations() {
		return mensurations;
	}

	public void setMensurations(Collection<Mensuration> mensurations) {
		this.mensurations = mensurations;
	}
	public void addMensuration(Mensuration mensurations) {
		this.mensurations.add(mensurations);
	}
	public void removeMensuration(Mensuration mensurations) {
		this.mensurations.remove(mensurations);
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
