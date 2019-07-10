package com.karma.dao;

import com.karma.model.Category;
import com.karma.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

//	void add(Category cate);
//
//	void update(Category cate);
//
//	void delete(Category cate);
//
//	Category get(int id);
//
//	List<Category> listCategory();
//
	@Query("SELECT c FROM Category c where c.name= :name")
	List<Category> search(@org.springframework.data.repository.query.Param("name") String name);

	@Query("SELECT c FROM Category c ")
	List<Category> list();
	
	@Query("SELECT p FROM Category c JOIN c.products p where p.name= :name")
	List<Product> searchByProduct(@org.springframework.data.repository.query.Param("name") String name);


}
