package com.boly.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.boly.entity.MensurationValue;

public interface MensurationValueRepository extends JpaRepository<MensurationValue, Long>{
	
}
