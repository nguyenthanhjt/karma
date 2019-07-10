package com.karma.dao;

import com.karma.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.name LIKE :name")
	List<Product> search(@Param("name") String name);
	
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.name LIKE :name")
	List<Product> searchByCategory(@Param("name") String name);
}
