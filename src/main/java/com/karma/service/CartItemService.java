package com.karma.service;

import com.karma.model.CartItem;

import java.util.List;

public interface CartItemService {

	void add(CartItem CartItemItem);

	void update(CartItem CartItem);

	CartItem get(int id);

	void delete(int id);

	List<CartItem> search(String name);

	List<CartItem> searchByProduct(String name);

}
