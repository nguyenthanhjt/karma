package com.karma.service.impl;

import com.karma.dao.CartItemDao;
import com.karma.model.CartItem;
import com.karma.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemDao cartItemDao;

	@Override
	public void add(CartItem cartItem) {
		cartItemDao.save(cartItem);
	}

	@Override
	public void update(CartItem cartItem) {
		CartItem currentCI = get(cartItem.getId());
		if (currentCI != null) {
			currentCI.setCart(cartItem.getCart());
			currentCI.setPrice(cartItem.getPrice());
			currentCI.setProduct(cartItem.getProduct());
			currentCI.setQuantity(cartItem.getQuantity());

			cartItemDao.save(currentCI);
		}

	}

	@Override
	public CartItem get(int id) {

		return cartItemDao.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		cartItemDao.deleteById(id);

	}

	@Override
	public List<CartItem> search(String name) {
		return cartItemDao.list();
	}

	@Override
	public List<CartItem> searchByProduct(String name) {
		// TODO Auto-generated method stub
		return cartItemDao.searchByProduct(name);
	}

}
