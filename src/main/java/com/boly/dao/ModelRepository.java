package com.boly.dao;


import org.springframework.data.jpa.repository.JpaRepository; 


import com.boly.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long>{
	
}
