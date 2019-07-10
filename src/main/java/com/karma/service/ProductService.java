package com.karma.service;

import com.karma.model.Product;

import java.util.List;

public interface ProductService {

	void add(Product p);

	void update(Product p);

	void delete(int id);

	Product get(int id);

	List<Product> listproduct(String name);

	List<Product> search(String name);
	
	List<Product> searchByCategory(String name);
	
}
