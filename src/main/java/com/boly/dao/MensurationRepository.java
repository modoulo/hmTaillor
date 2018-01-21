package com.boly.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.boly.entity.Mensuration;

public interface MensurationRepository extends JpaRepository<Mensuration, Long>{
	
}
