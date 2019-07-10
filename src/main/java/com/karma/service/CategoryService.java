package com.karma.service;

import com.karma.model.Category;
import com.karma.model.Product;

import java.util.List;

public interface CategoryService {
	void add(Category cate);

	void delete(int id);

	void update(Category cate);

	Category get(int id);

	List<Category> search(String name);
	
	List<Product> searchByProduct(String name);

	
}
