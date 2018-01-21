package com.boly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.boly.entity.Tailleur;

public interface TailleurRepository extends JpaRepository<Tailleur, Long> {
	public Optional<Tailleur> findTopByEmail(String email);
}
