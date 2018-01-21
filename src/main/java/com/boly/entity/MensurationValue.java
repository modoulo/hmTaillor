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
public class MensurationValue implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private Float valeur;
	@JsonIgnore
	@ManyToOne
	private Client client;
	@Transient
	private Long idClient;
	@ManyToOne
	private Mensuration mensuration;
	@Transient
	private Long idMensuration;
	public MensurationValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Float getValeur() {
		return valeur;
	}


	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Long getIdClient() {
		return idClient;
	}


	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}


	public Mensuration getMensuration() {
		return mensuration;
	}


	public void setMensuration(Mensuration mensuration) {
		this.mensuration = mensuration;
	}


	public Long getIdMensuration() {
		return idMensuration;
	}

	public void setIdMensuration(Long idTypeDhabit) {
		this.idMensuration = idTypeDhabit;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
