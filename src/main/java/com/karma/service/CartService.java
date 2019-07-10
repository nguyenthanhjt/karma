package com.karma.service;

import com.karma.model.Cart;

import java.util.List;

public interface CartService {

	void add(Cart cart);

	void update(Cart cart);

	Cart get(int id);

	void delete(int id);

	List<Cart> search(String name);

	List<Cart> searchByProduct(String name);

}
