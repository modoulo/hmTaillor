package com.boly.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.boly.entity.Catalogue;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long>{
	
}
