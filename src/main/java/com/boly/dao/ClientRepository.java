package com.boly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boly.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	public Optional<Client> findTopByEmail(String email);
}
